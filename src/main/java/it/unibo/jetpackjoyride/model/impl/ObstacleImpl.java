package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;
import it.unibo.jetpackjoyride.model.api.Obstacle;

/**
 * This is a class to model a generic obstacle.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public abstract class ObstacleImpl extends GameObject implements Obstacle{

    private boolean active;

    /**
     * Constructor of the Obstacle 
     * call back the constructor of the superclass
     * and set the obstacle state to active.
     * @param type
     * @param pos
     * @param vel
     */
    public ObstacleImpl(Point2d pos, Vector2d vel, Hitbox hitbox) {
        super(pos, vel, hitbox);
        this.setActiveOn();
    }
    
    public void setActiveOn(){
        this.active = true;
        this.getHitbox().setHitboxActive();
    }

    public void setActiveOff(){
        this.active = false;
        this.getHitbox().setHitboxDisable();
    }

    public boolean isActive(){
        return this.active;
    }
}
