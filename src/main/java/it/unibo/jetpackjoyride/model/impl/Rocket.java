package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * Class to model a Rocket obstacle.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Rocket extends ObstacleActivable {

    /**
     * Constructor to create a Rocket obstacle.
     * 
     * @param pos position
     * @param vel velocity
     * @param hitbox hitbox
     */
    public Rocket(final Point2d pos, final Vector2d vel, final Hitbox hitbox) {
        super(pos, vel, hitbox);
    }
}
