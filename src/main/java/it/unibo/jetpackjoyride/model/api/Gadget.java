package it.unibo.jetpackjoyride.model.api;

import java.util.List;
import java.util.Map;

/**
 * Interface for Gadget information.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public interface Gadget {
    
    /**
     * Get map of Gadget information.
     * @return map of Gadget information
     */
    Map<String, List<String>> getAll();

    /**
     * Getter of a Gadget.
     * 
     * @param name tha name of the Gadget to get the information
     * @return information of the Gadget
     */
    List<String> getValue(String name);

    /**
     * Setter for a Gadget.
     * 
     * @param name  the name of the Gadget
     * @param state the state of the Gadget (active or not)
     * @param purchased if the gadget was purchased true otherwise false
     * @param price the price of the gadget
     * @param description the description of the gadget
     */
    void setValue(String name, String state, String purchased, 
        String price, String description);

    
    /**
     * Setter for a Gadget.
     * @param name the name of the gadget
     * @param value the List of strings that contains the information of the gadget
     */
    void setValue(String name, List<String> value);

}
