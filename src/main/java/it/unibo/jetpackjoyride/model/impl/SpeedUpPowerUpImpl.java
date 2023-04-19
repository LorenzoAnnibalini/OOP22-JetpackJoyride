package it.unibo.jetpackjoyride.model.impl;


public class SpeedUpPowerUpImpl extends GameObject {

    private boolean active;
    private int distanceSpeedUp;

    public SpeedUpPowerUpImpl(int distanceSpeedUp) {
        this.distanceSpeedUp = distanceSpeedUp;
        this.active = false;
    }

    public int active() {
        this.active = true;
        return this.distanceSpeedUp;
    }

    public boolean isActive() {
        return this.active;
    }

}
