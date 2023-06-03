package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * Class that represents a speed up power up.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 * 
 */
public class SpeedUpPowerUpImpl extends GameObjectImpl {

    private boolean active;
    private final int distanceSpeedUp;

    /**
     *  Constructor for the speed up power up.
     * 
     * @param distanceSpeedUp
     * @param pos
     * @param vel
     * @param hitbox
     * 
     */
    public SpeedUpPowerUpImpl(final int distanceSpeedUp, final Point2d pos, final Vector2d vel, final Hitbox hitbox) {
        super(pos, vel, hitbox);
        this.distanceSpeedUp = distanceSpeedUp;
        this.active = false;
    }

    /**
     * Method that activates the speed up power up.
     * 
     * @return the distance of the speed up
     * 
     */
    public int active() {
        this.active = true;
        return this.distanceSpeedUp;
    }

    /**
     * Method that checks if the speed up power up is active or not.
     * 
     * @return true if the speed up power up is active, false otherwise
     * 
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Method that returns the distance of the speed up.
     * 
     * @return the distance of the speed up
     * 
     */
    public int getDistanceSpeedUp() {
        return this.distanceSpeedUp;
    }

}
