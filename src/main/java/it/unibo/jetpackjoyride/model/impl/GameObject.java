package it.unibo.jetpackjoyride.model.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * This is a class to model a generic game object.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class GameObject {

    private Point2d pos;
    private Vector2d vel;
    private final Hitbox hitbox;
    private static final double DELTA_TIME_MULTIPLIER = 0.001;

    /**
     * constructor to create a game object.
     * 
     * @param pos
     * @param vel
     * @param hitbox
     */
    public GameObject(final Point2d pos, final Vector2d vel, final Hitbox hitbox) {
        this.pos = new Point2d(pos.getX(), pos.getY());
        this.vel = new Vector2d(vel.getX(), vel.getY());
        this.hitbox = new HitboxImpl(hitbox.getHeigthHitbox(), hitbox.getWidthHitbox(), pos);
    }

    /**
     * set the position of the game object.
     * 
     * @param pos
     */
    public void setPos(final Point2d pos) {
        this.pos = new Point2d(pos.getX(), pos.getY());
    }

    /**
     * set the velocity of the game object.
     * 
     * @param vel
     */
    public void setVel(final Vector2d vel) {
        this.vel = new Vector2d(vel.getX(), vel.getY());
    }

    /**
     * flip the velocity of the game object on the y axis.
     */
    public void flipVelOnY() {
        this.vel = new Vector2d(vel.getX(), -vel.getY());
    }

    /**
     * flip the velocity of the game object on the x axis.
     */
    public void flipVelOnX() {
        this.vel = new Vector2d(-vel.getX(), vel.getY());
    }

    /**
     * update the state of a GameObject recalculating its position
     * from is current position plus
     * (the velocity of the object multiplied by a factor dt).
     * dt example: dt can be the time elapsed between two frames.
     * 
     * @param dt
     */
    public void updateState(final long dt) {
        this.pos = this.pos.sum(vel.mul(DELTA_TIME_MULTIPLIER * dt));
    }

    /**
     * get the current position of the game object.
     * 
     * @return the current position of the game object
     */
    public Point2d getCurrentPos() {
        return new Point2d(pos.getX(), pos.getY());
    }

    /**
     * get the current velocity of the game object.
     * 
     * @return the current velocity of the game object
     */
    public Vector2d getCurrentVel() {
        return new Vector2d(vel.getX(), vel.getY());
    }

    /**
     * get the current hitbox of the game object.
     * 
     * @return the current hitbox of the game object
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "the returned hitmox must be the same object")
    public final Hitbox getHitbox() {
        return this.hitbox;
    }
}
