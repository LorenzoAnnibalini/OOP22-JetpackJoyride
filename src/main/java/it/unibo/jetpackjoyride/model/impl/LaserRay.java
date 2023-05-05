package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * Class to model a LaserRay obstacle.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class LaserRay extends ObstacleActivable{

    /**
     * Constructor to create a LaserRay obstacle.
     * @param type
     * @param pos
     * @param vel
     */
    public LaserRay(Point2d pos, Vector2d vel,Hitbox hitbox) {
        super(pos, vel, hitbox);
    }
}
