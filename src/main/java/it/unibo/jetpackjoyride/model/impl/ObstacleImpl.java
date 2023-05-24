package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;
import it.unibo.jetpackjoyride.model.api.Obstacle;

/**
 * This is a class to model a generic obstacle.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public abstract class ObstacleImpl extends GameObject implements Obstacle {

    private boolean active;

    /**
     * Constructor of the Obstacle
     * call back the constructor of the superclass
     * and set the obstacle state to active.
     * 
     * @param pos position
     * @param vel velocity
     * @param hitbox hitbox
     */
    public ObstacleImpl(final Point2d pos, final Vector2d vel, final Hitbox hitbox) {
        super(pos, vel, hitbox);
        this.setActiveOn();
    }

    @Override
    public final void setActiveOn() {
        this.active = true;
        this.getHitbox().setHitboxActive();
    }

    @Override
    public final void setActiveOff() {
        this.active = false;
        this.getHitbox().setHitboxDisable();
    }

    @Override
    public final boolean isActive() {
        return this.active;
    }
}
