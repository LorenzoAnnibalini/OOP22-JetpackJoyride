package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.*;
import it.unibo.jetpackjoyride.model.api.Hitbox;



public class SpeedUpPowerUpImpl extends GameObject {

    private boolean active;
    private int distanceSpeedUp;

    public SpeedUpPowerUpImpl(int distanceSpeedUp, Point2d pos, Vector2d vel, Hitbox hitbox) {
        super(pos, vel, hitbox);
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
