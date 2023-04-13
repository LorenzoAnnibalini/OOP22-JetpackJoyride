package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Obstacle;

/**
 * This is a class to model a generic obstacle.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public abstract class ObstacleImpl extends GameObject implements Obstacle{

    private boolean active;
    private double lenght;
    private double width;

    /**
     * Constructor of the Obstacle 
     * call back the constructor of the superclass
     * and set the obstacle state to active.
     * @param type
     * @param pos
     * @param vel
     */
    public ObstacleImpl(Point2d pos, Vector2d vel, double lenght, double width) {
        super(pos, vel);
        this.setActiveOn();
        this.lenght = lenght;
        this.width = width;
    }
    
    public void setActiveOn(){
        this.active = true;
    }

    public void setActiveOff(){
        this.active = false;
    }

    public boolean isActive(){
        return this.active;
    }

    public double getLenght() {
        return lenght;
    }

    public double getwidth() {
        return width;
    }
}
