package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;

/**
 * Class to model an Electrode obstacle.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Electrode extends Obstacle{

    /**
     * Constructor to create an Electrode obstacle.
     * @param type
     * @param pos
     * @param vel
     */
    public Electrode(Type type, Point2d pos, Vector2d vel) {
        super(type, pos, vel);
    }
}
