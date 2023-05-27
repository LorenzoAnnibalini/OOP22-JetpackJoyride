package it.unibo.jetpackjoyride.common;

/**
 * This is a class to model two-dimensional point.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class Point2d {

    private double x, y;

    /**
     * getter for x.
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * setter for x.
     * @param x coordinate
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * getter for y.
     * @return y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * setter for y.
     * @param y coordinate
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Constructor to create a 2d point (x,y).
     * 
     * @param x
     * @param y
     */
    public Point2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method to sum a vector v to this point.
     * 
     * @param v
     * @return new point
     */
    public Point2d sum(final Vector2d v) {
        return new Point2d(this.x + v.getX(), this.y + v.getY());
    }

    /**
     * Method to subtract a vector v to this point.
     * 
     * @param v
     * @return new point
     */
    public Point2d sub(final Vector2d v) {
        return new Point2d(this.x - v.getX(), this.y - v.getY());
    }

    /**
     * Method to print this point.
     * 
     * @return this point
     */
    @Override
    public String toString() {
        return "Point2d(" + this.x + "," + this.y + ")";
    }

}
