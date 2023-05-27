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
    enum PlayerDirection {
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
    boolean isStatusPlayer();

    /**
     * Function for add a heart to the player.
     */
    void addHeart();

    /**
     * Function for remove a heart to the player.
     */
    void removeHeart();

    /**
     * Function for set the direction of the player to UP.
     */
    void setDirectionUP();

    /**
     * Function for set the direction of the player to DOWN.
     */
    void setDirectionDOWN();

    /**
     * Function for set the direction of the player to STATIC.
     */
    void setDirectionSTATIC();

    /**
     * Function for get the direction of the player.
     * 
     * @return the direction of the player.
     */
    PlayerDirection getDirection();

    /**
     * Function for get the number of hearts of the player.
     * 
     * @return the number of hearts of the player.
     */
    int getHearts();

    /**
     * Function for get the statistics of the player.
     * 
     * @return the statistics of the player.
     */
    StatisticsImpl getStatistics();

}
