package it.unibo.jetpackjoyride.model.impl;
import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * This is a class to model a generic hitbox.
 * @author mattia.burreli@studio.unibo.it
*/
public class HitboxImpl implements Hitbox {

    private Point2d upLeftPoint;
    private Point2d downRightPoint;
    private int height;
    private int width;

    /**
     * Constructor of the Hitbox.
     * Set the hitbox size and the position of the main points.
     * @param height
     * @param width
     */
    public HitboxImpl(final int height, final int width, final Point2d posObject) {
        this.height = height;
        this.width = width;
        this.upLeftPoint = this.calcPointPosition(posObject, "-");
        this.downRightPoint = this.calcPointPosition(posObject, "+");
    }

    @Override
    public int getWidthHitbox() {
       return this.width;
    }

    @Override
    public int getHeigthHitbox() {
        return this.height;
    }

    @Override
    public Point2d getPointUpLeft() {
       return this.upLeftPoint;
    }

    @Override
    public Point2d getPointDownRight() {
        return this.downRightPoint;
    }

    @Override
    public void updateHitbox(Point2d posObject) {
        this.upLeftPoint = this.calcPointPosition(posObject, "-");
        this.downRightPoint = this.calcPointPosition(posObject, "+");
    }
    
    /**
     * method for calculating the position of a point based on the object's position and the size of its hitbox
     * @param posObject
     * @param sign
     * @return the position of the calculated point.
     */
    private Point2d calcPointPosition(final Point2d posObject,String sign){
        if(sign.equals("-")){
            return new Point2d(posObject.x - this.width/2, posObject.y - this.height/2);
        } else if (sign.equals("+")){
            return new Point2d(posObject.x + this.width/2, posObject.y + this.height/2);
        } else {
            throw new IllegalArgumentException("Sign must be '+' or '-'");
        }
    }
}
