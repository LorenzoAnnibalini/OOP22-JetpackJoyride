package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.model.api.PowerUp;

/**
 * Automatic PowerUp Implementation, the user can only use the getter
 * The costractor set the powerup to enable, is active until the duration is over
 * The player need to call isActive() to know if the powerup is still active
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class PowerUpImpl extends GameObject implements PowerUp {

    private boolean active;
    private final PowerUpType type;
    private int coinCost;
    private long duration;
    private long startTime;

    public PowerUpImpl(final PowerUpType type, final int cost, final long duration) {
        if(type == null)throw new IllegalArgumentException("Type cannot be null");
        this.type = type;
        this.setCost(cost);
        this.setDuration(duration);
        this.active = true;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public boolean isActive() {
        if(System.currentTimeMillis() - this.startTime > this.duration) {
            this.active = false;
        }
        return this.active;
    }

    @Override
    public PowerUpType getType() {
        return this.type;
    }

    @Override
    public int getCost() {
        return this.coinCost;
    }

    @Override
    public long getDuration() {
       return this.duration;
    }

    @Override
    public long getStartTime() {
        return this.startTime;
    }

    /*
     * Set the cost of the powerup
     */
    private void setCost(int cost) {
        if(cost < 0)throw new IllegalArgumentException("Cost must be positive");
        this.coinCost = cost;
    }

    /*
     * Set the duration of the powerup
     */
    private void setDuration(long duration) {
        if(duration < 0)throw new IllegalArgumentException("Duration must be positive");
        this.duration = duration;
    }

    
}
