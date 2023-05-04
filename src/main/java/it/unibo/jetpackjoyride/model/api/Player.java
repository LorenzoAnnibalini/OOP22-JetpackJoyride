package it.unibo.jetpackjoyride.model.api;

/**
 * Interface for the player. 
 * 
 * @author mattia.burreli@studio.unibo.it
**/

public interface Player {
    
    /**
     * Function for get the status of player.
     * @return true if the player is alive, else return false.
     */
    public boolean getStatusPlayer();

    /**
     * Function for add a heart to the player.
     */
    public void addHeart();

    /**
     * Function for remove a heart to the player.
     */
    public void removeHeart();

}
