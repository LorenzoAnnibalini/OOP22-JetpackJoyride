package it.unibo.jetpackjoyride.model.api;

import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

/**
 * Interface for the player.
 * 
 * @author mattia.burreli@studio.unibo.it
 **/

public interface Player {

    /**
     * Enum for the direction of the player.
     */
    public enum PlayerDirection {
        /**
         * Direction of the player to UP.
         */
        UP,
        /**
         * Direction of the player to DOWN.
         */
        DOWN,
        /**
         * Direction of the player to STATIC.
         */
        STATIC
    }

    /**
     * Function for get the status of player.
     * 
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

    /**
     * Function for get the direction of the player.
     * 
     * @return the direction of the player.
     */
    public PlayerDirection getDirection();

    /**
     * Function for get the number of hearts of the player.
     * 
     * @return the number of hearts of the player.
     */
    public int getHearts();

    /**
     * Function for get the statistics of the player.
     * 
     * @return the statistics of the player.
     */
    public StatisticsImpl getStatistics();

}
