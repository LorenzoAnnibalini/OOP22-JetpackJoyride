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

public class WorldGameStateImpl implements WorldGameState{
    
    private Statistics runStatistics;
    private EntitiesGenerator entitiesGenerator;
    private PlayerImpl player;
    private List<Money> money;
    private Set<Pair<String,GameObject>> entities;

    public WorldGameStateImpl() {
        this.runStatistics = new StatisticsImpl();
        this.entitiesGenerator=new EntitiesGeneratorImpl();
        this.runStatistics.addStatistic("money", 0);
        this.runStatistics.addStatistic("score", 0);
        this.player=new PlayerImpl(new Point2d(50,350), new Vector2d(50, 350),new HitboxImpl(15, 10, new Point2d(50,350)));
    }


    public void updateState(final long elapsedTime){
        this.updateEntities(elapsedTime);
        
    }

    private void newEntities(){
        
    }

    private void updateEntities(final long elapsedTime){
        this.entities.stream().forEach(entity -> entity.getY().updateState(elapsedTime));
        this.player.updateState(elapsedTime);
        this.money.stream().forEach(moneyElem -> moneyElem.updateState(elapsedTime));
    }





}

