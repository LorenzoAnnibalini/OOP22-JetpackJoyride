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
    public Point2d getWidthHitbox();

    /**
     * Method to get the height of the hitbox.
     * @return the position of the hitbox.
     */
    public Point2d getHeigthHitbox();

    /**
     * Method to update the position of the hitbox.
     */
    public void updateHitbox();

}
