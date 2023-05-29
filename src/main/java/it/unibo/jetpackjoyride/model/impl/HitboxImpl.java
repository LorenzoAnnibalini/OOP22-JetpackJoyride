package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * This is a class to model a generic hitbox.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public final class HitboxImpl implements Hitbox {

    private Point2d upLeftPoint;
    private Point2d downRightPoint;
    private final Double height;
    private final Double width;
    private boolean hitboxActive;

    /**
     * Constructor of the Hitbox.
     * Set the hitbox size and the position of the main points.
     * 
     * @param height
     * @param width
     * @param posObject
     */
    public HitboxImpl(final Double height, final Double width, final Point2d posObject) {
        this.height = height;
        this.width = width;
        this.hitboxActive = true;
        this.calcPointPosition(posObject);
    }

    @Override
    public Double getWidthHitbox() {
        return this.width;
    }

    @Override
    public Double getHeigthHitbox() {
        return this.height;
    }

    @Override
    public Point2d getPointUpLeft() {
        return new Point2d(this.upLeftPoint.getX(), this.upLeftPoint.getY());
    }

    @Override
    public Point2d getPointDownRight() {
        return new Point2d(this.downRightPoint.getX(), this.downRightPoint.getY());
    }

    @Override
    public void updateHitbox(final Point2d posObject) {
        this.calcPointPosition(posObject);
    }

    /**
     * method for calculating the position of a point based on the object's
     * position.
     * and the size of its hitbox.
     * 
     * @param posObject
     */
    private void calcPointPosition(final Point2d posObject) {
        this.upLeftPoint = new Point2d(posObject.getX() - this.width / 2.0, posObject.getY() - this.height / 2.0);
        this.downRightPoint = new Point2d(posObject.getX() + this.width / 2.0, posObject.getY() + this.height / 2.0);
    }

    @Override
    public void setHitboxActive() {
        this.hitboxActive = true;
    }

    @Override
    public boolean isHitboxActive() {
        return this.hitboxActive;
    }

    @Override
    public void setHitboxDisable() {
        this.hitboxActive = false;
    }

    @Override
    public boolean checkCollision(final Hitbox hitbox) {
        return (this.checkCollisionHitboxAndPoint(hitbox.getPointUpLeft())
                || this.checkCollisionHitboxAndPoint(hitbox.getPointDownRight())
                || this.checkCollisionHitboxAndPoint(
                        new Point2d(hitbox.getPointUpLeft().getX(), hitbox.getPointDownRight().getY()))
                || this.checkCollisionHitboxAndPoint(
                        new Point2d(hitbox.getPointDownRight().getX(), hitbox.getPointUpLeft().getY())))
                && hitbox.isHitboxActive() && this.hitboxActive;
    }

    /**
     * Method to check if a point is in collision with the hitbox.
     * 
     * @param point
     * @return true if the point is in collision with the hitbox, false otherwise.
     */
    private boolean checkCollisionHitboxAndPoint(final Point2d point) {
        return point.getX() >= this.upLeftPoint.getX() && point.getX() <= this.downRightPoint.getX()
                && point.getY() >= this.upLeftPoint.getY() && point.getY() <= this.downRightPoint.getY();
    }

}
