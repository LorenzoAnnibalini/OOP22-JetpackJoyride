package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;
/**
 * Abstract class to model an ObstacleActivable, 
 * a particular obstacle that can activate itself
 * after a time called changeStateTime.
 */
public abstract class ObstacleActivable extends ObstacleImpl {

    private final long creationTime;
    private static final long CHANGE_STATE_TIME = 4000;
    private long activationTime;
    private boolean end;

    /**
     * Constructor of the ObstacleActivable
     * call back the constructor of the superclass.
     * set the obstacle state to inactive and set the
     * creationTime to the current time.
     * 
     * @param pos position
     * @param vel velocity
     * @param hitbox hitbox
     */
    public ObstacleActivable(final Point2d pos, final Vector2d vel, final Hitbox hitbox) {
        super(pos, vel, hitbox);
        this.setActiveOff();
        this.end = false;
        this.creationTime = System.currentTimeMillis();
    }

    /**
     * Update the state of an ObstacleActivable by
     * activating/deactivating the obstacle if a
     * changeStateTime is elapsed.
     * 
     * @param dt time elapsed
     */
    public void checkState(final long dt) {
        if ((System.currentTimeMillis() - this.creationTime > CHANGE_STATE_TIME)
                && !this.isActive()) {
            this.activationTime = System.currentTimeMillis();
            this.setActiveOn();
        }
        if (this.isActive()
                && (System.currentTimeMillis() - this.activationTime > CHANGE_STATE_TIME)) {
            this.setActiveOff();
            this.setEndOn();
        }
    }

    /**
     * Set the end state of the ObstacleActivable to true.
     */
    private void setEndOn() {
        this.end = true;
    }

    /**
     * Get the end state of the ObstacleActivable.
     * 
     * @return this.end
     */
    public boolean isEnd() {
        return this.end;
    }
}
