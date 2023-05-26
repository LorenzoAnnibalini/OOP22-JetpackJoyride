package it.unibo.jetpackjoyride.model.api;

/**
 * Interface for the scientist.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public interface Scientist {

    /**
     * Method to get the Scientist's direction.
     * 
     * @return the current direction of the scientist
     */
    Direction getDirection();

    /**
     * Method to get if the scientist is alive.
     * 
     * @return true if the scientist is alive, false otherwise
     */
    Boolean isAlive();

    /**
     * Method to kill the Scientist's.
     * Set the life of the scientist to false.
     * 
     */
    void killScientist();

    /**
     * Method to update the position of the scientist based on his direction.
     * 
     */
    void nextPosition();

}
