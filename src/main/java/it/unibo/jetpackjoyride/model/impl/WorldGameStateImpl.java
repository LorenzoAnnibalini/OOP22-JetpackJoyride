package it.unibo.jetpackjoyride.model.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.EntitiesGenerator;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.api.WorldGameState;
import it.unibo.jetpackjoyride.model.api.Player.PlayerDirection;
import it.unibo.jetpackjoyride.core.api.MoneyPatternLoader;

public class WorldGameStateImpl implements WorldGameState {

    private static final int FRAME_HEIGHT = 1800;
    private Statistics runStatistics;
    private EntitiesGenerator entitiesGenerator;
    private PlayerImpl player;
    private List<Money> money;
    private Set<Pair<String, GameObject>> entities;

    public WorldGameStateImpl() {
        this.inizializeWorldState();
    }

    @Override
    public void updateState(final long elapsedTime) {
        this.checkBoardPlayerCollision();
        this.updateEntities(elapsedTime);
        this.entitiesGarbage();
        this.checkPlayerCollision();
        this.newEntities();
    }

    /**
     * Create new entities in the world.
     */
    private void newEntities() {

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
            if (this.player.getHitbox().checkCollision(entity.getY().getHitbox())) {
                switch (entity.getX()) {
                    case "Rocket":
                        this.player.removeHeart();
                        this.entities.remove(entity);
                        break;
                    case "Electrode":
                        this.player.removeHeart();
                        this.entities.remove(entity);
                        break;
                    case "SpeedPowerUp":
                        this.entities.remove(entity);
                        break;
                    case "ShieldPowerUp":
                        this.player.addHeart();
                        this.entities.remove(entity);
                        break;
                    case "Laser":
                        this.player.removeHeart();
                        this.entities.remove(entity);
                        break;
                    case "Scientist":
                        this.entities.remove(entity);
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
                this.runStatistics.increment("money");
                this.money.remove(moneyElem);
            }
        }

    }

    /**
     * Check if the player is colliding with the upper board or with the lower
     * board.
     */
    private void checkBoardPlayerCollision() {
        if (Math.abs((this.player.getHitbox().getHeigthHitbox() / 2) - this.player.getHitbox().getPointUpLeft().y) <= 0
                && this.player.getDirection() == PlayerDirection.UP) {
            this.player.setDirectionSTATIC();
        }
        if (Math.abs((this.player.getHitbox().getHeigthHitbox() / 2)
                + this.player.getHitbox().getPointUpLeft().y) >= FRAME_HEIGHT
                && this.player.getDirection() == PlayerDirection.DOWN) {
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
            if (entity.getY().getCurrentPos().x < 0) {
                this.entities.remove(entity);
            }
        }

        while (moneyIterator.hasNext()) {
            Money moneyElem = moneyIterator.next();
            if (moneyElem.getCurrentPos().x < 0) {
                this.money.remove(moneyElem);
            }
        }

    }

    /**
     * Initialize the world state, setting the player, the statistics and the
     * entities.
     */
    private void inizializeWorldState() {
        this.runStatistics = new StatisticsImpl();
        this.entitiesGenerator = new EntitiesGeneratorImpl();
        this.runStatistics.addStatistic("money", 0);
        this.runStatistics.addStatistic("score", 0);
        this.money = new ArrayList<>();
        this.player = new PlayerImpl(new Point2d(50, 350), new Vector2d(50, 350),
                new HitboxImpl(15, 10, new Point2d(50, 350)));
        this.entitiesGenerator.generateEntity(entities, 3);
        this.entitiesGenerator.generateScientists(2);
        this.entities = this.entitiesGenerator.getEntities();
    }

    /**
     * Update the state of all the entities in the world.
     * 
     * @param elapsedTime
     */
    private void updateEntities(final long elapsedTime) {
        this.entities.stream().forEach(entity -> entity.getY().updateState(elapsedTime));
        this.player.updateState(elapsedTime);
        this.money.stream().forEach(moneyElem -> moneyElem.updateState(elapsedTime));
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
    public Statistics getWorldStatistics() {
        return this.runStatistics;
    }

    @Override
    public void newGame() {
        this.inizializeWorldState();
    }

}
