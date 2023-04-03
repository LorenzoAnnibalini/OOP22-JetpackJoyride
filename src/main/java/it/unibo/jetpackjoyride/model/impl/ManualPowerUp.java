package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.model.api.PowerUp;

/**
 * Manual powerup, need to be activated by the player
 * The costractor set the powerup to disabled, need to be activated after
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class ManualPowerUp implements PowerUp{

    private boolean active;
    private final PowerUpType type;
    private int coinCost;
    private int duration;

    public ManualPowerUp(final PowerUpType type, final int cost, final int duration) {
        if(type == null)throw new IllegalArgumentException("Type cannot be null");
        this.type = type;
        this.disable();
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
