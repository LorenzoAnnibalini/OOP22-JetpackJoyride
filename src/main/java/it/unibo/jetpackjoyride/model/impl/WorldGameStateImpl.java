package it.unibo.jetpackjoyride.model.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    private static final Double HEIGHT_PLAYER = 40.0;
    private static final Double WIDTH_PLAYER = 50.0;
    private static final int GENERATION_OBSTACLES_PROBABILITY = 75;
    private static final int DELTA_TIME_ENTITIES = 1500;
    private StatisticsImpl runStatistics;
    private EntitiesGenerator entitiesGenerator;
    private PlayerImpl player;
    private List<Money> money;
    private Set<Pair<String, GameObject>> entities;
    private long previousCycleStartTime;
    private final MoneyPatternLoader moneyPatternLoader;
    private final SavesImpl saves;
    private final Random random;
    private boolean isFlying;
    private int deciderEntitiesGenerator; // 0 = entities, 1 = money, 2 = laser
    private int timeToWaitNewEntities;
    private final InputQueue inputHandler;
    private final Statistics generalStatistics;
    private static final String SUPPVALUE = "EI_EXPOSE_REP2";
    private static final String SUPPJUSTIFICATION = "InputHandler are meant to be the same for GameEngine, view and world";

    /**
     * Constructor for the world game state. It inzialize the world with his
     * entities, the general statistics and the load the saves.
     * 
     * @param inputHandler
     * @throws IOException
     */
    @SuppressFBWarnings(value = SUPPVALUE, justification = SUPPJUSTIFICATION)
    public WorldGameStateImpl(final InputQueue inputHandler) throws IOException {
        this.inputHandler = inputHandler;
        this.saves = new SavesImpl();
        this.moneyPatternLoader = new MoneyPatternLoaderImpl();
        this.random = new Random();
        final SkinInfoLoaderImpl skinInfoLoader = new SkinInfoLoaderImpl();
        try {
            skinInfoLoader.downloadSkin();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Error during dowload of the skin", e);
        }
        final GadgetLoaderImpl gadgetLoader = new GadgetLoaderImpl();
        try {
            gadgetLoader.downloadGadget();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Error during dowload of the gadget", e);
        }
        this.generalStatistics = new StatisticsImpl();
        try {
            this.generalStatistics.setAll(this.saves.downloadSaves());
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Error during dowload of the saves", e);
        }
    }

    @Override
    public void updateState(final long elapsedTime) throws IOException {
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
        if (this.player.isStatusPlayer()) {
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
     * 
     * @throws IOException
     */
    private void newEntities() throws IOException {
        final long currentCycleStartTime = System.currentTimeMillis();
        final long timePassed = currentCycleStartTime - this.previousCycleStartTime;
        if (timePassed >= this.timeToWaitNewEntities && this.deciderEntitiesGenerator == 0) {
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
        } else if (timePassed >= this.timeToWaitNewEntities && this.deciderEntitiesGenerator == 1) {
            this.money.addAll(moneyPatternLoader.getMoneyPattern());
            this.previousCycleStartTime = currentCycleStartTime;
            this.timeToWaitNewEntities = this.timeToWait();
            this.deciderEntitiesGenerator = this.randomDecider();
        } else if (timePassed >= this.timeToWaitNewEntities && this.deciderEntitiesGenerator == 2
                && this.entities.isEmpty()) {
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
        final Iterator<Pair<String, GameObject>> entityIterator = this.entities.iterator();
        while (entityIterator.hasNext()) {
            final Pair<String, GameObject> entity = entityIterator.next();
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
                        final SpeedUpPowerUpImpl speedUp = (SpeedUpPowerUpImpl) entity.getY();
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
        final Iterator<Money> moneyIterator = this.money.iterator();
        while (moneyIterator.hasNext()) {
            final Money moneyElem = moneyIterator.next();
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
        final Iterator<Pair<String, GameObject>> entityIterator = this.entities.iterator();
        final Iterator<Money> moneyIterator = this.money.iterator();
        while (entityIterator.hasNext()) {
            final Pair<String, GameObject> entity = entityIterator.next();
            if (entity.getY().getCurrentPos().getX() < 0
                    || entity.getY().getCurrentPos().getX() > FRAME_WIDTH + VOID_SPACE_ON_RIGHT) {
                entityIterator.remove();
            }
        }

        while (moneyIterator.hasNext()) {
            final Money moneyElem = moneyIterator.next();
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
        final Point2d playerPosition = new Point2d(X_PLAYER_POSITION, Y_PLAYER_POSITION);
        final Vector2d playerVelocity = new Vector2d(playerPosition, playerPosition);
        this.timeToWaitNewEntities = this.timeToWait();
        this.previousCycleStartTime = System.currentTimeMillis();
        this.player = new PlayerImpl(playerPosition, playerVelocity,
                new HitboxImpl(HEIGHT_PLAYER, WIDTH_PLAYER, playerPosition), this.runStatistics);
        try {
            this.generalStatistics.setAll(this.saves.downloadSaves());
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("The generalStatistics are not downloaded", e);
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
                final Rocket rocket = (Rocket) entity.getY();
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
     * 
     * @throws IOException
     */
    private void notifyEndGame() throws IOException {
        this.inputHandler.addInput(new InputImpl(Input.TypeInput.END_GAME, "endGame"));
        this.generalStatistics.increment("Deaths");

        this.generalStatistics.updateGeneralStats(this.runStatistics.getAll());
        this.saves.uploadSaves(this.generalStatistics.getAll());
    }

    /**
     * Update the time entities. If the time of the entity is ended the entity will
     * change its state or doing something related to the entity.
     */
    private void updateTimeLaser() {
        final Iterator<Pair<String, GameObject>> entityIterator = this.entities.iterator();
        if (this.deciderEntitiesGenerator == 2
                && this.entities.stream().filter(entity -> entity.getX().matches("Laser")).count() != 0) {
            while (entityIterator.hasNext()) {
                final Pair<String, GameObject> laserRay = entityIterator.next();
                final LaserRay laserRayObj = (LaserRay) laserRay.getY();
                laserRayObj.checkState(1);
                if (laserRayObj.isEnd()) {
                    entityIterator.remove();
                    if (this.entities.isEmpty()) {
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
        final int valueRandom = random.nextInt(GENERAL_PROBABILITY);
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
            throw new IllegalStateException("The game can't be started", e);
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
