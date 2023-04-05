package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;

/**
 * Class to model a LaserRay obstacle.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class LaserRay extends ObstacleImpl{

    private long creationTime;
    private long changeStateTime = 40000;
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
        creationTime = System.currentTimeMillis();
    }
 
    /**
     * Update the state of the LaserRay obstacle by
     * updating his postion calling back the superclass gameObject
     * method and by activating/deactivating the obstacles if a
     * changeStateTime is elapsed.
     */
    public void updateState(long dt){
        super.updateState(dt);
        if((System.currentTimeMillis() - this.creationTime > this.changeStateTime) && !this.isActive()){
            this.activationTime = System.currentTimeMillis();
            this.setActiveOn();
        }
        if((System.currentTimeMillis() - this.activationTime > this.changeStateTime) && this.isActive()){
            this.setActiveOff();
        }
    }
}
