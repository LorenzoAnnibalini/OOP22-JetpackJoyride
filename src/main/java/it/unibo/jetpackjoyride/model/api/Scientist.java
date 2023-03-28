package it.unibo.jetpackjoyride.model.api;

/**
 * TODO: Descrizione interfaccia
 * @author lorenzo.annibalini@studio.unibo.it
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
     * Method to set the Scientist's isAlive to false
     */
    public void killScientist();
    
}
