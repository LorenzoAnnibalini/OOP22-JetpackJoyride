package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;

/**
 * This is a class to model a generic obstacle.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public abstract class Obstacle extends GameObject{

    public Obstacle(Type type, Point2d pos, Vector2d vel) {
        super(type, pos, vel);
        //TODO Auto-generated constructor stub
    }
    
}
