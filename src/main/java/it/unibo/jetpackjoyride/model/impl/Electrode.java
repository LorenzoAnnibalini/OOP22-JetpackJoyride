package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;
import it.unibo.jetpackjoyride.model.api.Orientation;

/**
 * Class to model an Electrode obstacle.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Electrode extends ObstacleImpl {

    private final Orientation orientation;

    /**
     * Constructor to create an Electrode obstacle.
     * 
     * @param type
     * @param pos
     * @param vel
     */
    public Electrode(final Point2d pos, final Vector2d vel, final Orientation orientation, final Hitbox hitbox) {
        super(pos, vel, hitbox);
        this.orientation = orientation;
    }

    /**
     * get the orientation of the Electrode.
     * 
     * @return the orientation of the Electrode
     */
    public Orientation getOrientation() {
        return this.orientation;
    }
}
