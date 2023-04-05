package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;

/**
 * Class to model a LaserRay obstacle.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class LaserRay extends ObstacleImpl{

    private long creationTime;
    private final long changeStateTime = 40000;
    private long activationTime;

    /**
     * Constructor to create a LaserRay obstacle.
     * @param type
     * @param pos
     * @param vel
     */
    public LaserRay(Type type, Point2d pos, Vector2d vel) {
        super(type, pos, vel);
        this.setActiveOff();
        this.creationTime = System.currentTimeMillis();
    }
 
    /**
     * Update the state of the LaserRay obstacle by
     * activating/deactivating the obstacles if a
     * changeStateTime is elapsed.
     * doesn't change the position calling back the superclass
     * GameObject method because LaserRay is a fixed obstacle.
     */
    public void updateState(long dt){
        if((System.currentTimeMillis() - this.creationTime > this.changeStateTime) 
            && !this.isActive()){
            this.activationTime = System.currentTimeMillis();
            this.setActiveOn();
        }
        if(this.isActive()
            && (System.currentTimeMillis() - this.activationTime > this.changeStateTime)){
            this.setActiveOff();
        }
    }
}
