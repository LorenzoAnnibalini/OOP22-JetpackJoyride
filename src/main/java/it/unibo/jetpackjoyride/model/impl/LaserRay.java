package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * Class to model a LaserRay obstacle.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class LaserRay extends ObstacleActivable {

    /**
     * Constructor to create a LaserRay obstacle.
     * 
     * @param type
     * @param pos
     * @param vel
     */
    public LaserRay(final Point2d pos, final Vector2d vel, final Hitbox hitbox) {
        super(pos, vel, hitbox);
    }
}
