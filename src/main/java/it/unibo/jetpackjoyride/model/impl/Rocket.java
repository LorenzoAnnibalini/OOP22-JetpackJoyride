package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;

/**
 * Class to model a Rocket obstacle.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Rocket extends ObstacleActivable{

    /**
     * Constructor to create a Rocket obstacle.
     * @param type
     * @param pos
     * @param vel
     */
    public Rocket(Point2d pos, Vector2d vel) {
        super(pos, vel);
    }
}
