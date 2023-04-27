package it.unibo.jetpackjoyride.model.impl;

import java.util.List;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Player;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.api.WorldGameState;

public class WorldGameStateImpl implements WorldGameState{
    
    private Statistics runStatistics;
    private Set<Pair<String, GameObject>> entities;
    private Player player;
    private List<Money> money;

    public WorldGameStateImpl() {
        this.runStatistics = new StatisticsImpl();
        this.runStatistics.addStatistic("money", 0);
        this.runStatistics.addStatistic("score", 0);
        this.player=new PlayerImpl(new Point2d(50,350), new Vector2d(50, 350),new HitboxImpl(15, 10, new Point2d(50,350)));
    }

    



}
