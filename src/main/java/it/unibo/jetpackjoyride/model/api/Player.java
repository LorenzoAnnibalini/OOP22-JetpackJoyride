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
     * Sets status player to false. 
     */
    public void setPlayerDeath();

    /**
     * Sets status player to true.
     */
    public void setPlayerAlive();

}
