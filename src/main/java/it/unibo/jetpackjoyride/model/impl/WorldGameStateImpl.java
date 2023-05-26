package it.unibo.jetpackjoyride.model.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.EntitiesGenerator;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.api.WorldGameState;
import it.unibo.jetpackjoyride.core.api.MoneyPatternLoader;
import it.unibo.jetpackjoyride.core.impl.GadgetLoaderImpl;
import it.unibo.jetpackjoyride.core.impl.MoneyPatternLoaderImpl;
import it.unibo.jetpackjoyride.core.impl.SavesImpl;
import it.unibo.jetpackjoyride.core.impl.SkinInfoLoaderImpl;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.input.api.Input;

/**
 * Implementation of the world game state. It contains the entities and the
 * world,
 * the main statistics of the run and status updates of the entities and the
 * world.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public final class WorldGameStateImpl implements WorldGameState {

    private static final int FRAME_HEIGHT = 550;
    private static final int FRAME_WIDTH = 1240;
    private static final int VOID_SPACE_ON_RIGHT = 300;
    private static final int SCIENTIST_NUMBER = 2;
    private static final int START_NUMBER_DECIDER = 0;
    private static final int GENERAL_PROBABILITY = 100;
    private static final int MONEY_PROBABILITY = 20;
    private static final int LASER_PROBABILITY = 11;
    private static final int ENTITY_PROBABILITY = 69;
    private static final int X_PLAYER_POSITION = 200;
    private static final int Y_PLAYER_POSITION = 200;
    private static final int HEIGHT_PLAYER = 40;
    private static final int WIDTH_PLAYER = 50;
    private static final int GENERATION_OBSTACLES_PROBABILITY = 75;
    private static final int DELTA_TIME_ENTITIES = 1500;
    private StatisticsImpl runStatistics;
    private EntitiesGenerator entitiesGenerator;
    private PlayerImpl player;
    private Point2d playerPosition;
    private Vector2d playerVelocity;
    private List<Money> money;
    private Set<Pair<String, GameObject>> entities;
    private long previousCycleStartTime;
    private long timePassed;
    private MoneyPatternLoader moneyPatternLoader;
    private SavesImpl saves;
    private Random random;
    private boolean isFlying;
    private int deciderEntitiesGenerator; // 0 = entities, 1 = money, 2 = laser
    private int timeToWaitNewEntities;
    private InputQueue inputHandler;
    private Statistics generalStatistics;
    private SkinInfoLoaderImpl skinInfoLoader;
    private GadgetLoaderImpl gadgetLoader;

    /**
     * Constructor for the world game state. It inzialize the world with his
     * entities, the general statistics and the load the saves.
     * 
     * @param inputHandler
     * @throws IOException
     */
    public WorldGameStateImpl(final InputQueue inputHandler) throws IOException {
        this.inputHandler = inputHandler;
        this.generalStatistics = new StatisticsImpl();
        this.saves = new SavesImpl();
        this.moneyPatternLoader = new MoneyPatternLoaderImpl();
        this.random = new Random();
        this.skinInfoLoader = new SkinInfoLoaderImpl();
        this.gadgetLoader = new GadgetLoaderImpl();
        try {
            this.skinInfoLoader.downloadSkin();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.gadgetLoader.downloadGadget();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.generalStatistics.setAll(this.saves.downloadSaves());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateState(final long elapsedTime) {
        if (!this.isFlying) {
            this.player.setDirectionDOWN();
        } else {
            this.player.setDirectionUP();
        }
        this.checkBoardPlayerCollision();
        this.updateTimeLaser();
        this.updateEntities(elapsedTime);
        this.entitiesGarbage();
        this.checkPlayerCollision();
        if (this.player.getStatusPlayer()) {
            this.newEntities();
            this.runStatistics.increment("TotalMeters");
        } else {
            this.notifyEndGame();
        }
    }

    /**
     * Create new entities in the world. If the number of entities is less than a
     * certain number and the time passed is greater than a certain time and
     * the decider is 0, create new entities. Else if the decider is 1, create new
     * money. Else if the decider is 2 and there are no entities, create new
     * lasers. If there are no scientists in the world, create new scientists.
     */
    private void newEntities() {
        long currentCycleStartTime = System.currentTimeMillis();
        this.timePassed = currentCycleStartTime - this.previousCycleStartTime;
        if (this.timePassed >= this.timeToWaitNewEntities && this.deciderEntitiesGenerator == 0) {
            if (this.random.nextInt(100) < GENERATION_OBSTACLES_PROBABILITY) {
                this.entitiesGenerator.generateObstacles(entities, this.random.nextInt(3) + 2);
                this.entities = this.entitiesGenerator.getEntities();
            } else {
                this.entitiesGenerator.generatePowerUps(entities, 1);
                this.entities = this.entitiesGenerator.getEntities();
            }
            this.entities = this.entitiesGenerator.getEntities();
            this.previousCycleStartTime = currentCycleStartTime;
            this.timeToWaitNewEntities = this.timeToWait();
            this.deciderEntitiesGenerator = this.randomDecider();
        } else if (this.timePassed >= this.timeToWaitNewEntities && this.deciderEntitiesGenerator == 1) {
            try {
                this.money.addAll(moneyPatternLoader.getMoneyPattern());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.previousCycleStartTime = currentCycleStartTime;
            this.timeToWaitNewEntities = this.timeToWait();
            this.deciderEntitiesGenerator = this.randomDecider();
        } else if (this.timePassed >= this.timeToWaitNewEntities && this.deciderEntitiesGenerator == 2
                && this.entities.size() == 0) {
            this.entitiesGenerator.generateLaser(this.entities, random.nextInt(4));
            this.entities = this.entitiesGenerator.getEntities();
            this.previousCycleStartTime = currentCycleStartTime;
        }

        if (this.entities.stream()
                .filter(entity -> entity.getX().matches("Scientist"))
                .count() <= 0 && this.deciderEntitiesGenerator != 2) {
            this.entitiesGenerator.generateScientists(this.entities, SCIENTIST_NUMBER);
            this.entities = this.entitiesGenerator.getEntities();
        }
    }

    /**
     * Check if the player is colliding with an entity or a money.
     * If collide with an entity check the type of the entity and do the right
     * action. If collide with a money add the money to the player's money.
     * For all the entities that are not colliding with the player they are
     * eliminated.
     */
    private void checkPlayerCollision() {
        Iterator<Pair<String, GameObject>> entityIterator = this.entities.iterator();
        Iterator<Money> moneyIterator = this.money.iterator();
        while (entityIterator.hasNext()) {
            Pair<String, GameObject> entity = entityIterator.next();
            if (entity.getY().getHitbox().checkCollision(this.player.getHitbox())
                    || player.getHitbox().checkCollision(entity.getY().getHitbox())) {
                switch (entity.getX()) {
                    case "Rocket":
                        this.player.removeHeart();
                        entityIterator.remove();
                        break;
                    case "Electrode":
                        this.player.removeHeart();
                        entityIterator.remove();
                        break;
                    case "SpeedUpPowerup":
                        SpeedUpPowerUpImpl speedUp = (SpeedUpPowerUpImpl) entity.getY();
                        this.runStatistics.increment("TotalMeters", speedUp.getDistanceSpeedUp());
                        this.runStatistics.increment("GrabbedObjects");
                        entityIterator.remove();
                        break;
                    case "ShieldPowerUp":
                        this.player.addHeart();
                        this.runStatistics.increment("GrabbedObjects");
                        entityIterator.remove();
                        break;
                    case "Laser":
                        this.player.removeHeart();
                        entityIterator.remove();
                        break;
                    case "Scientist":
                        this.runStatistics.increment("KilledNpc");
                        entityIterator.remove();
                        break;
                    case "Nothing":
                        break;
                    default:
                        throw new IllegalArgumentException("The type of Entity is NULL or is incorrect.");
                }

            }
        }
        while (moneyIterator.hasNext()) {
            Money moneyElem = moneyIterator.next();
            if (this.player.getHitbox().checkCollision(moneyElem.getHitbox())) {
                this.runStatistics.increment("GrabbedMoney");
                moneyIterator.remove();
            }
        }

    }

    /**
     * Check if the player is colliding with the upper board or with the lower
     * board.
     */
    private void checkBoardPlayerCollision() {
        if (this.player.getHitbox().getPointUpLeft().getY() <= 0 && this.isFlying) {
            this.player.setDirectionSTATIC();
        } else if (this.player.getHitbox().getPointDownRight().getY() >= FRAME_HEIGHT && !this.isFlying) {
            this.player.setDirectionSTATIC();
        }

    }

    /**
     * Method to check if an entity is out of visible range and so has to be
     * deleted.
     */
    private void entitiesGarbage() {
        Iterator<Pair<String, GameObject>> entityIterator = this.entities.iterator();
        Iterator<Money> moneyIterator = this.money.iterator();
        while (entityIterator.hasNext()) {
            Pair<String, GameObject> entity = entityIterator.next();
            if (entity.getY().getCurrentPos().getX() < 0
                    || entity.getY().getCurrentPos().getX() > FRAME_WIDTH + VOID_SPACE_ON_RIGHT) {
                entityIterator.remove();
            }
        }

        while (moneyIterator.hasNext()) {
            Money moneyElem = moneyIterator.next();
            if (moneyElem.getCurrentPos().getX() < 0) {
                moneyIterator.remove();
            }
        }

    }

    /**
     * Return the time to wait for the next entities generation.
     * 
     * @return the time to wait.
     */
    private int timeToWait() {
        return random.nextInt(DELTA_TIME_ENTITIES) + DELTA_TIME_ENTITIES;
    }

    /**
     * Initialize the world state, setting the player, the statistics and the
     * entities.
     * 
     * @throws IOException
     */
    private void inizializeWorldGameState() throws IOException {
        this.runStatistics = new StatisticsImpl();
        this.entitiesGenerator = new EntitiesGeneratorImpl();
        this.entities = new HashSet<>();
        this.money = new ArrayList<>();
        this.runStatistics.addStatistic("GrabbedMoney", 0);
        this.runStatistics.addStatistic("TotalMeters", 0);
        this.runStatistics.addStatistic("KilledNpc", 0);
        this.runStatistics.addStatistic("GrabbedObjects", 0);
        this.isFlying = false;
        this.timeToWaitNewEntities = this.timeToWait();
        this.previousCycleStartTime = System.currentTimeMillis();
        this.playerPosition = new Point2d(X_PLAYER_POSITION, Y_PLAYER_POSITION);
        this.playerVelocity = new Vector2d(this.playerPosition, this.playerPosition);
        this.player = new PlayerImpl(this.playerPosition, this.playerVelocity,
                new HitboxImpl(HEIGHT_PLAYER, WIDTH_PLAYER, this.playerPosition), this.runStatistics);
        try {
            this.generalStatistics.setAll(this.saves.downloadSaves());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.deciderEntitiesGenerator = START_NUMBER_DECIDER;
    }

    /**
     * Update the state of all the entities in the world.
     * 
     * @param elapsedTime
     */
    private void updateEntities(final long elapsedTime) {
        this.entities.stream().forEach(entity -> {
            if (entity.getX().matches("Rocket")) {
                Rocket rocket = (Rocket) entity.getY();
                if (!rocket.isActive()) {
                    rocket.checkState(elapsedTime);
                } else {
                    entity.getY().updateState(elapsedTime);
                    entity.getY().getHitbox().updateHitbox(entity.getY().getCurrentPos());
                }
            } else {
                entity.getY().updateState(elapsedTime);
                entity.getY().getHitbox().updateHitbox(entity.getY().getCurrentPos());
            }

        });
        this.player.updateState(elapsedTime);
        this.player.getHitbox().updateHitbox(this.player.getCurrentPos());
        this.money.stream().forEach(moneyElem -> {
            moneyElem.updateState(elapsedTime);
            moneyElem.getHitbox().updateHitbox(moneyElem.getCurrentPos());
        });

    }

    /**
     * Notify that the game is ended at the game engine.
     */
    private void notifyEndGame() {
        this.inputHandler.addInput(new InputImpl(Input.TypeInput.END_GAME, "endGame"));
        this.generalStatistics.increment("Deaths");

        this.generalStatistics.updateGeneralStats(this.runStatistics.getAll());
        try {
            this.saves.uploadSaves(this.generalStatistics.getAll());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Update the time entities. If the time of the entity is ended the entity will
     * change its state or doing something related to the entity.
     */
    private void updateTimeLaser() {
        Iterator<Pair<String, GameObject>> entityIterator = this.entities.iterator();
        if (this.deciderEntitiesGenerator == 2
                && this.entities.stream().filter(entity -> entity.getX().matches("Laser")).count() != 0) {
            while (entityIterator.hasNext()) {
                Pair<String, GameObject> laserRay = entityIterator.next();
                LaserRay laserRayObj = (LaserRay) laserRay.getY();
                laserRayObj.checkState(1);
                if (laserRayObj.isEnd()) {
                    entityIterator.remove();
                    if (this.entities.size() == 0) {
                        this.deciderEntitiesGenerator = this.randomDecider();
                    }
                }

            }
        }

    }

    /**
     * Method to generate a random number between 0 and 3. This number is used to
     * decide what type of object has to be generated.
     * 
     * @return the number generated.
     */
    private int randomDecider() {
        int valueRandom = random.nextInt(GENERAL_PROBABILITY);
        if (valueRandom < ENTITY_PROBABILITY) {
            return 0;
        } else if (valueRandom < ENTITY_PROBABILITY + MONEY_PROBABILITY) {
            return 1;
        } else if (valueRandom < ENTITY_PROBABILITY + MONEY_PROBABILITY + LASER_PROBABILITY) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public PlayerImpl getPlayer() {
        return this.player;
    }

    @Override
    public List<Money> getMoney() {
        return this.money;
    }

    @Override
    public Set<Pair<String, GameObject>> getWorldEntities() {
        return this.entities;
    }

    @Override
    public void newGame() {
        try {
            this.inizializeWorldGameState();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveUp() {
        this.isFlying = true;
        this.player.setDirectionUP();
    }

    @Override
    public void moveStatic() {
        this.isFlying = false;
        this.player.setDirectionSTATIC();
    }

    @Override
    public Statistics getGeneralStatistics() {
        return this.generalStatistics;
    }

}
