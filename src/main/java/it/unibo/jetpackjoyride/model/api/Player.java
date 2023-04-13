package it.unibo.jetpackjoyride.model.api;


/**
 * Interface for the player.
 * @author mattia.burreli@studio.unibo.it
 */
public interface Player {

    /**
     * This method returns the status of the player.
     * @return true if the player is alive, false otherwise
     */
    public boolean getStatusPlayer();

    /**
     * This method sets the status of the player to dead (false).
     */
    public void setPlayerDeath();

    /**
     * This method sets the status of the player to alive (true).
     */
    public void setPlayerAlive();

}