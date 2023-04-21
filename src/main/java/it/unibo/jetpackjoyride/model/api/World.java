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
     * Method to add an entity to the world.
     * @param entity the entity to add
     */
    void setEntities(Set<Pair<String, GameObject>> entities);

    /**
     * Method to get the score.
     * 
     * @return the score
     */
    int getMeters();

    /**
     * Method to set new value of meters.
     */
    void setMeters(int meters);

    /**
     * Method to get the money reached in the game.
     * 
     * @return the money
     */
    int getMoney();

    /**
     * Method to set new value of money.
     * @param money the new value of money
     */
    void setMoney(int money);

}
