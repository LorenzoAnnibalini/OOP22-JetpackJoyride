package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

public abstract class ObstacleActivable extends ObstacleImpl{

    private long creationTime;
    private final long changeStateTime = 4000;
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
    public ObstacleActivable(Point2d pos, Vector2d vel,Hitbox hitbox) {
        super(pos, vel,hitbox);
        this.setActiveOff();
        this.creationTime = System.currentTimeMillis();
    }
    
    /**
     * Update the state of an ObstacleActivable by
     * activating/deactivating the obstacle if a
     * changeStateTime is elapsed.
     */
    public void checkState(long dt){
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
