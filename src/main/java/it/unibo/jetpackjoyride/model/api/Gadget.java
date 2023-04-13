package it.unibo.jetpackjoyride.model.api;

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
    Map<String, Boolean> getAll();


    /**
     * Getter of a Gadget.
     * 
     * @param name tha name of the Gadget to get the information
     * @return information of the Gadget
     */
    Boolean getValue(String name);

    /**
     * Setter for a Gadget.
     * 
     * @param name  the name of the Gadget
     * @param state the state of the Gadget (active or not)
     */
    void setValue(String name, Boolean state);
}
