package it.unibo.jetpackjoyride.model.api;

import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.impl.GameObject;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;

/**
 * Class to modelize the world of the game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public interface World {

    /**
     * Method to get the player.
     * 
     * @return the player
     */
    PlayerImpl getPlayer();

    /**
     * Method to get the entities currently spawned in the world.
     * 
     * @return the entities
     */
    Set<Pair<String, GameObject>> getEntities();

    /**
     * Method to get the score.
     * 
     * @return the score
     */
    int getMeters();

    /**
     * Method to get the money reached in the game.
     * 
     * @return the money
     */
    int getMoney();
}
