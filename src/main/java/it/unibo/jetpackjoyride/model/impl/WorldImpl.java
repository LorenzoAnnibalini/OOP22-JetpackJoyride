package it.unibo.jetpackjoyride.model.impl;

import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.World;

public class WorldImpl implements World{

    private PlayerImpl player;
    private Set<Pair<String, GameObject>> entities;
    private int meters = 0;
    private int money = 0;

    public WorldImpl(Set<Pair<String, GameObject>> entities) {
        this.entities = entities;
    }

    @Override
    public PlayerImpl getPlayer() {
        return this.player;
    }

    @Override
    public Set<Pair<String, GameObject>> getEntities() {
        return this.entities;
    }

    @Override
    public int getMeters() {
        return this.meters;
    }

    @Override
    public int getMoney() {
        return this.money;
    }
    
}
