package it.unibo.jetpackjoyride.model.api;

import java.util.Map;

import it.unibo.jetpackjoyride.common.Pair;

/**
 * Interface for Gadget information.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public interface Gadget {
    
    /**
     * Get map of Gadget information.
     * @return map of Gadget information
     */
    Map<String, Pair<Boolean, Boolean>> getAll();

    /**
     * Getter of a Gadget.
     * 
     * @param name tha name of the Gadget to get the information
     * @return information of the Gadget
     */
    Pair<Boolean, Boolean> getValue(String name);

    /**
     * Setter for a Gadget.
     * 
     * @param name  the name of the Gadget
     * @param state the state of the Gadget (active or not)
     * @param purchased if the gadget was purchased true otherwise false
     */
    void setValue(String name, Boolean state, Boolean purchased);
}
