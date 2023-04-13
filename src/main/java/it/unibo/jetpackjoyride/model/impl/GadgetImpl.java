package it.unibo.jetpackjoyride.model.impl;

import java.util.HashMap;
import java.util.Map;
import it.unibo.jetpackjoyride.model.api.Gadget;

/**
 * Class to modelize the Gadget information.
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class GadgetImpl implements Gadget{

    private static Map<String, Boolean> gadget = new HashMap<>();

    @Override
    public Map<String, Boolean> getAll() {
        return gadget;
    }

    @Override
    public Boolean getValue(String name) {
        return gadget.get(name);
    }

    @Override
    public void setValue(String name, Boolean state) {
        gadget.replace(name, state);
    }

    /**
     * Method to set all the values of the Gadget.
     * 
     * @param gadgetMap the map of names and values to set
     */
    public static void setAll(Map<String, Boolean> gadgetMap) {
        gadget.putAll(gadgetMap);
    }
}
