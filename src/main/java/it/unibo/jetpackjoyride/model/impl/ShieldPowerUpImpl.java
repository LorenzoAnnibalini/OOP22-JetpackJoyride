package it.unibo.jetpackjoyride.model.impl;

public class ShieldPowerUpImpl extends GameObject {

    private boolean active;
    private long duration;
    private long startTime;

    public ShieldPowerUpImpl(long duration) {
        this.duration = duration;
        this.active = false;
        this.startTime = 0;
    }

    public boolean isActive() {
        if (this.active) {
            if (System.currentTimeMillis() - this.startTime > this.duration) {
                this.active = false;
            }
        }
        return this.active;
    }

    public long getDuration() {
        return this.duration;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setIsActive(boolean active) {
        this.active = active;
        this.startTime = System.currentTimeMillis();
    }
     
}