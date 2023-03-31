package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;

/**
 * This is a class to model a generic game object.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class GameObject {

	public static enum Type { BARRY, SCIENTIST, LASERRAY, ROCKET, ELECTRODE }

	private Type type;
	private Point2d pos;
	private Vector2d vel;
	
    /**
     * constructor to create a game object.
     * @param type
     * @param pos
     * @param vel
     */
	public GameObject(final Type type, final Point2d pos, final Vector2d vel){
		this.type = type;
		this.pos = pos;
		this.vel = vel;
	}
	
    /**
     * get the type of the game object.
     * @return type of game object
     */
	public Type getType(){
		return type;
	}
	
    /**
     * set the position of the game object.
     * @param pos
     */
	public void setPos(final Point2d pos){
		this.pos = pos;
	}

    /**
     * set the velocity of the game object.
     * @param vel
     */
	public void setVel(final Vector2d vel){
		this.vel = vel;
	}

    /**
     * flip the velocity of the game object on the y axis.
     */
	public void flipVelOnY(){
		this.vel = new Vector2d(vel.x, -vel.y);
	}

    /**
     * flip the velocity of the game object on the x axis.
     */
	public void flipVelOnX(){
		this.vel = new Vector2d(-vel.x, vel.y);
	}

    /**
     * update the state of a GameObject recalculating its position
     * from is current position plus 
     * (the velocity of the object multiplied by a factor dt).
     * dt example: dt can be the time elapsed between two frames. 
     * @param dt
     */
    public void updateState(long dt){
        this.pos = this.pos.sum(vel.mul(0.001 * dt));
    }
	
    /**
     * get the current position of the game object.
     * @return the current position of the game object
     */
	public Point2d getCurrentPos(){
		return pos;
	}
	
    /**
     * get the current velocity of the game object.
     * @return the current velocity of the game object
     */
	public Vector2d getCurrentVel(){
		return vel;
	}
}
