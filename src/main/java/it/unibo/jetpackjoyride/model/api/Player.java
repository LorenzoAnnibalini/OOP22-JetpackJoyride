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

    /**
     * Function for set the direction of the player to UP.
     */
    public void setDirectionUP();

    /**
     * Function for set the direction of the player to DOWN.
     */
    public void setDirectionDOWN();

    /**
     * Function for set the direction of the player to STATIC.
     */
    public void setDirectionSTATIC();

}
