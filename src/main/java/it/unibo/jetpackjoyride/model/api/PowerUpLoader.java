package it.unibo.jetpackjoyride.model.api;

import java.util.Map;
import it.unibo.jetpackjoyride.common.Pair;

/**
 * Interface to load PowerUp information.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public interface PowerUpLoader {
    
    /**
     * Get map of PowerUp information.
     * @return map of PowerUp information
     */
    Map<String, Pair<String, Integer>> getAll();


    /**
     * Getter of a powerUp.
     * 
     * @param name tha name of the powerUp to get the information
     * @return information of the PowerUp
     */
    int getValue(String name);

    /**
     * Setter for a powerUp.
     * 
     * @param name  the name of the PowerUp
     * @param value the type of the PowerUp
     * @param level the level of the PowerUp
     */
    void setValue(String name, String type, int level);

    /**
     * Method to increment the level of a PowerUp.
     * 
     * @param name the name of the PowerUp
     */
    void increment(String name);
}
