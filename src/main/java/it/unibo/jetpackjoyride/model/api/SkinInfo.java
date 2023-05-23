package it.unibo.jetpackjoyride.model.api;

import java.util.List;
import java.util.Map;

/**
 * Interface to get the Skin information.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public interface SkinInfo {
    /**
     * Get map of Skin information.
     * 
     * @return map of Skin information
     */
    Map<String, List<String>> getAll();

    /**
     * Getter of a Skin.
     * 
     * @param name tha name of the Skin to get the information
     * @return information of the Skin
     */
    List<String> getValue(String name);

    /**
     * Setter for a Skin.
     * 
     * @param name      the name of the Skin
     * @param state     the state of the Skin (active or not)
     * @param purchased if the Skin was purchased true otherwise false
     * @param price     the price of the skin
     */
    void setValue(String name, String state, String purchased, String price);

    /**
     * Setter for a Skin.
     * 
     * @param name  of the skin
     * @param value list of strings that contains the information of the skin
     */
    void setValue(String name, List<String> value);

}
