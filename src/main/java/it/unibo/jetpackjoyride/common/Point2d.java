package it.unibo.jetpackjoyride.common;

/**
 * This is a class to model two-dimensional point
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Point2d {
    
    public double x, y;

    /**
     * Constructor to create a 2d point (x,y).
     * @param x
     * @param y
     */
    public Point2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Method to sum a vector v to this point.
     * @param v
     * @return new point
     */
    public Point2d sum(final Vector2d v){
        return new Point2d(this.x + v.x, this.y + v.y);
    }

    /**
     * Method to subtract a vector v to this point.
     * @param v
     * @return new point
     */
    public Point2d sub(final Vector2d v){
        return new Point2d(this.x - v.x, this.y - v.y);
    }

    /**
     * Method to print this point.
     * @return this point
     */
    public String toString(){
        return "Point2d("+this.x+","+this.y+")";
    }
    
}
