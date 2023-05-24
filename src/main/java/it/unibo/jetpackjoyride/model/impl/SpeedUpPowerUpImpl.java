package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.*;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/*
 * Class that represents a speed up power up
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class SpeedUpPowerUpImpl extends GameObject {

    private boolean active;
    private final int distanceSpeedUp;

    public SpeedUpPowerUpImpl(final int distanceSpeedUp, final Point2d pos, final Vector2d vel, final Hitbox hitbox) {
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
