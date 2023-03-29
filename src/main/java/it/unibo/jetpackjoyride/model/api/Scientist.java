package it.unibo.jetpackjoyride.model.api;

/**
 * TODO: Descrizione interfaccia
 * @author lorenzo.annibalini@studio.unibo.it
 */

/**
 * Enum to represent the direction of the scientist
 * Can't be changed
 */
enum Direction {
    RIGHT,
    LEFT
    }

public interface Scientist {
    
    /**
     * Method to get the Scientist's direction
     * @return the current direction of the scientist
     */
    public Direction getDirection();

    /**
     * Method to get if the scientist is alive
     * @return true if the scientist is alive, false otherwise
     */
    public Boolean isAlive();

    /**
     * Method to kill the Scientist's
     * Set the life of the scientist to false
     */
    public void killScientist();
    
}
