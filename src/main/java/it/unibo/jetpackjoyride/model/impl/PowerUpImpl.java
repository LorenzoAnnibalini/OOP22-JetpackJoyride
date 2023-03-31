package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.model.api.PowerUp;

/**
 * Generic PowerUp implementation
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class PowerUpImpl implements PowerUp{

    private boolean active;
    private final PowerUpType type;
    private int coinCost;
    private int duration;

    public PowerUpImpl(final PowerUpType type, final int cost, final int duration) {
        this.type = type;
        this.setCost(cost);
        this.setDuration(duration);
    }

    @Override
    public void active() {
        this.active = true;
    }

    @Override
    public void disable() {
       this.active = false;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public PowerUpType getType() {
        return this.type;
    }

    @Override
    public void setCost(int cost) {
        if(cost < 0)throw new IllegalArgumentException("Cost must be positive");
        this.coinCost = cost;
    }

    @Override
    public int getCost() {
        return this.coinCost;
    }

    @Override
    public void setDuration(int duration) {
        if(duration < 0)throw new IllegalArgumentException("Duration must be positive");
        this.duration = duration;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }
    
}
