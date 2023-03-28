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

    
}
