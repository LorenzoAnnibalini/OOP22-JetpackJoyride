package it.unibo.jetpackjoyride.model.api;

import it.unibo.jetpackjoyride.common.Point2d;

/**
 * Interface for the hitbox.
 * @author mattia.burreli@studio.unibo.it
*/
public interface Hitbox {
    
    /**
     * Method to get the width of the hitbox.
     * @return the position of the hitbox.
     */
    public int getWidthHitbox();

    /**
     * Method to get the height of the hitbox.
     * @return the position of the hitbox.
     */
    public int getHeigthHitbox();

    /**
     * Method to get the position of the up-left point of hitbox.
     * @return the position of the up-left point.
     */
    public Point2d getPointUpLeft();

    /**
     * Method to get the position of the down-right point of hitbox.
     * @return the position of the down-right point.
     */
    public Point2d getPointDownRight();

    /**
     * Method to update the position of the hitbox and main points.
     */
    public void updateHitbox(final Point2d posObject);

}
