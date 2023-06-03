package it.unibo.jetpackjoyride.model.api;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;

/**
 * Interface to model a generic GameObject.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public interface GameObject {

    /**
     * set the position of the game object.
     * 
     * @param pos
     */
    void setPos(Point2d pos);

    /**
     * set the velocity of the game object.
     * 
     * @param vel
     */
    void setVel(Vector2d vel);

    /**
     * flip the velocity of the game object on the y axis.
     */
    void flipVelOnY();

    /**
     * flip the velocity of the game object on the x axis.
     */
    void flipVelOnX();

    /**
     * update the state of a GameObject recalculating its position
     * from is current position plus
     * (the velocity of the object multiplied by a factor dt).
     * dt example: dt can be the time elapsed between two frames.
     * 
     * @param dt
     */
    void updateState(long dt);

    /**
     * get the current position of the game object.
     * 
     * @return the current position of the game object
     */
    Point2d getCurrentPos();

    /**
     * get the current velocity of the game object.
     * 
     * @return the current velocity of the game object
     */
    Vector2d getCurrentVel();

    /**
     * get the current hitbox of the game object.
     * 
     * @return the current hitbox of the game object
     */
    Hitbox getHitbox();

}
