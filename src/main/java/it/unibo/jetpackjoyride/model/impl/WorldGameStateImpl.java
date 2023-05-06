package it.unibo.jetpackjoyride.model.impl;

import java.util.List;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.EntitiesGenerator;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.api.WorldGameState;
import it.unibo.jetpackjoyride.core.api.MoneyPatternLoader;

public class WorldGameStateImpl implements WorldGameState {

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
        this.updateEntities(elapsedTime);
        this.entitiesGenerator.entitiesGarbage(entities);
        this.entities = this.entitiesGenerator.getEntities();
    }

    private void newEntities() {

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
