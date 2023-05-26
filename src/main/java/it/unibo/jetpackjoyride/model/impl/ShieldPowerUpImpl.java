package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/*
 * Class that represents a shield power up
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class ShieldPowerUpImpl extends GameObject {

    private boolean active;
    private final long duration;
    private long startTime;

    /**
     * Constructor for the shield power up
     * @param duration
     * @param pos
     * @param vel
     * @param hitbox
     */
    public ShieldPowerUpImpl(final long duration, final Point2d pos, final Vector2d vel, final Hitbox hitbox) {
        super(pos, vel, hitbox);
        this.duration = duration;
        this.active = false;
        this.startTime = 0;
    }

    /**
     * Method that checks if the shield is active or not
     * @return true if the shield is active, false otherwise
     */
    public boolean isActive() {
        if (this.active) {
            if (System.currentTimeMillis() - this.startTime > this.duration) {
                this.active = false;
            }
        }
        return this.active;
    }

    /**
     * Method that returns the duration of the shield
     * @return the duration of the shield
     */
    public long getDuration() {
        return this.duration;
    }

    /**
     * Method that returns the time when the shield has been activated
     * @return the time when the shield has been activated
     */
    public long getStartTime() {
        return this.startTime;
    }

    /**
     * Method that sets the shield active or not
     * @param active true if the shield is active, false otherwise
     */
    public void setIsActive(final boolean active) {
        this.active = active;
        this.startTime = System.currentTimeMillis();
    }

}
