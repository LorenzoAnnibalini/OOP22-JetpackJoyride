package it.unibo.jetpackjoyride.model.api;

import it.unibo.jetpackjoyride.common.Point2d;

/**
 * Interface for the hitbox.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public interface Hitbox {

    /**
     * Method to get the width of the hitbox.
     * 
     * @return the position of the hitbox.
     */
    Double getWidthHitbox();

    /**
     * Method to get the height of the hitbox.
     * 
     * @return the position of the hitbox.
     */
    Double getHeigthHitbox();

    /**
     * Method to get the position of the up-left point of hitbox.
     * 
     * @return the position of the up-left point.
     */
    Point2d getPointUpLeft();

    /**
     * Method to get the position of the down-right point of hitbox.
     * 
     * @return the position of the down-right point.
     */
    Point2d getPointDownRight();

    /**
     * Method to update the position of the hitbox and main points.
     * 
     * @param posObject
     */
    void updateHitbox(Point2d posObject);

    /**
     * Method to set the hitbox active.
     */
    void setHitboxActive();

    /**
     * Method to check if the hitbox is active.
     * 
     * @return true if the hitbox is active, false otherwise.
     */
    boolean isHitboxActive();

    /**
     * Method to set the hitbox disable.
     */
    void setHitboxDisable();

    /**
     * Method to check if two hitboxes are in collision.
     * 
     * @param hitbox
     * @return true if the hitboxes are in collision, false otherwise.
     */
    boolean checkCollision(Hitbox hitbox);

}
