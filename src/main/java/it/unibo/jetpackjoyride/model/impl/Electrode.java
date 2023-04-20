package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * Class to model an Electrode obstacle.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Electrode extends ObstacleImpl{

    public static enum Orientation { HORIZONTAL, VERTICAL }

    private final Orientation orientation;
    
    /**
     * Constructor to create an Electrode obstacle.
     * @param type
     * @param pos
     * @param vel
     */
    public Electrode(Point2d pos, Vector2d vel, Orientation orientation,Hitbox hitbox) {
        super(pos, vel,hitbox);
        this.orientation = orientation;
    }

    /**
     * get the orientation of the Electrode.
     * @return the orientation of the Electrode
     */
    public Orientation getOrientation(){
        return this.orientation;
    }
}
