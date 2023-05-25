package it.unibo.jetpackjoyride.model.api;

import java.util.List;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.impl.GameObject;
import it.unibo.jetpackjoyride.model.impl.Money;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;

/**
 * Interface for the game state and the world.
 * contains the entities and the world, the main statistics of the run
 * and status updates of the entities and the world.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public interface WorldGameState {

    /**
     * Get the player.
     * 
     * @return the player entity;
     */
    PlayerImpl getPlayer();

    /**
     * Get money etities.
     * 
     * @return money entities;
     */
    List<Money> getMoney();

    /**
     * Get all entities.
     * 
     * @return all entities;
     */
    Set<Pair<String, GameObject>> getWorldEntities();

    /**
     * Get the general statistics of the game.
     * 
     * @return general statistics
     */
    Statistics getGeneralStatistics();

    /**
     * Update the status of the world and its entities.
     * 
     * @param elapsedTime
     */
    void updateState(long elapsedTime);

    /**
     * Start a new game.
     */
    void newGame();

    /**
     * Move the player up.
     */
    void moveUp();

    /**
     * Stop the player and set his direction to static.
     */
    void moveStatic();

}
