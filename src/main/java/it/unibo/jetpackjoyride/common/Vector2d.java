package it.unibo.jetpackjoyride.common;

/**
 * This is a class to model two-dimensional vector.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Vector2d {

    public double x, y;

    /**
     * Constructor to create a 2d vector (x,y).
     * 
     * @param x
     * @param y
     */
    public Vector2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor to create a 2d vector from two points.
     * 
     * @param to
     * @param from
     */
    public Vector2d(final Point2d to, final Point2d from) {
        this.x = to.x - from.x;
        this.y = to.y - from.y;
    }

    /**
     * Method to sum a vetcor v to this vector.
     * 
     * @param v
     * @return a new vector
     */
    public Vector2d sum(final Vector2d v) {
        return new Vector2d(this.x + v.x, this.y + v.y);
    }

    /**
     * Method to get the module of this vector.
     * 
     * @return the module of this vector
     */
    public double module() {
        return (double) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Method to get the normalized vector of this vector.
     * 
     * @return a new vector
     */
    public Vector2d getNormalized() {
        final double module = this.module();
        return new Vector2d(this.x / module, this.y / module);
    }

    /**
     * Method to multiply this vector for a fact factor.
     * 
     * @param fact
     * @return a new vector
     */
    public Vector2d mul(final double fact) {
        return new Vector2d(this.x * fact, this.y * fact);
    }

    /**
     * Method to print this vector.
     * 
     * @return this vector
     */
    public String toString() {
        return "Vector2d(" + this.x + "," + this.y + ")";
    }
}
