package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;

/**
 * This is a class to model a generic obstacle.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public abstract class Obstacle extends GameObject{

    private boolean active;

    /**
     * Constructor of the Obstacle 
     * that only recall the constructor of the superclass.
     * @param type
     * @param pos
     * @param vel
     */
    public Obstacle(Type type, Point2d pos, Vector2d vel) {
        super(type, pos, vel);
    }
    
    /**
     * Set the obstacle active = true.
     */
    public void setActiveOn(){
        this.active = true;
    }

    /**
     * Set the obstacle active = false.
     */
    public void setActiveOff(){
        this.active = false;
    }

    /**
     * Return true if the obstacle is active, false otherwise.
     * @return true if obstacle is active, false otherwise
     */
    public boolean isActive(){
        return this.active;
    }
}
