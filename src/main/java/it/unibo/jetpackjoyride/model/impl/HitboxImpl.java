package it.unibo.jetpackjoyride.model.impl;
import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * This is a class to model a generic hitbox.
 * @author mattia.burreli@studio.unibo.it
*/
public class HitboxImpl implements Hitbox  {

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
        this.upLeftPoint = new Point2d((posObject.x-(width/2)),posObject.y+(height/2));
        this.downRightPoint = new Point2d((posObject.x-(width/2)),posObject.y+(height/2));
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
    public void updateHitbox(final Point2d posObject) {
        
    }

    @Override
    public Point2d getPointUpLeft() {
       return this.upLeftPoint;
    }

    @Override
    public Point2d getPointDownRight() {
        return this.downRightPoint;
    }
    
}
