package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.*;
import it.unibo.jetpackjoyride.model.api.Hitbox;

public class ShieldPowerUpImpl extends GameObject {

    private boolean active;
    private long duration;
    private long startTime;

    public ShieldPowerUpImpl(long duration, Point2d pos, Vector2d vel, Hitbox hitbox) {
        super(pos, vel, hitbox);
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