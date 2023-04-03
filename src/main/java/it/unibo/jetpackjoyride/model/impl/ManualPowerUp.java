package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.model.api.PowerUp;

/**
 * Manual PowerUp Impementation, the costractor set the powerup to disabled
 * This class can't modify the active state, the user have to call active() and disable() to change it
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class ManualPowerUp implements PowerUp{

    private boolean active;
    private final PowerUpType type;
    private int coinCost;
    private long duration;
    private long startTime;

    public ManualPowerUp(final PowerUpType type, final int cost, final long duration) {
        if(type == null)throw new IllegalArgumentException("Type cannot be null");
        this.type = type;
        this.setCost(cost);
        this.setDuration(duration);
        this.disable();
        this.startTime = System.currentTimeMillis();
    }

    public void active() {
        this.active = true;
    }

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

    /**
     * Set the cost in coin of the powerup
     * @param cost
     * @throws IllegalArgumentException if cost is negative
     */
    public void setCost(int cost) {
        if(cost < 0)throw new IllegalArgumentException("Cost must be positive");
        this.coinCost = cost;
    }

    @Override
    public int getCost() {
        return this.coinCost;
    }

    /**
     * Set the duration of the powerup
     * @param duration
     * @throws IllegalArgumentException if duration is negative
     */
    public void setDuration(long duration) {
        if(duration < 0)throw new IllegalArgumentException("Duration must be positive");
        this.duration = duration;
    }

    @Override
    public long getDuration() {
        return this.duration;
    }

    @Override
    public long getStartTime() {
        return this.startTime;
    }
    
}
