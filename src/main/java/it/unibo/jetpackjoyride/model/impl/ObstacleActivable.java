package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;

public abstract class ObstacleActivable extends ObstacleImpl{

    private long creationTime;
    private final long changeStateTime = 40000;
    private long activationTime;

    /**
     * Constructor of the ObstacleActivable
     * call back the constructor of the superclass.
     * set the obstacle state to inactive and set the
     * creationTime to the current time.
     * @param type
     * @param pos
     * @param vel
     */
    public ObstacleActivable(Type type, Point2d pos, Vector2d vel) {
        super(type, pos, vel);
        this.setActiveOff();
        this.creationTime = System.currentTimeMillis();
    }
    
    /**
     * Update the state of an ObstacleActivable by
     * activating/deactivating the obstacle if a
     * changeStateTime is elapsed.
     * doesn't change the position calling back the superclass
     * GameObject method.
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
