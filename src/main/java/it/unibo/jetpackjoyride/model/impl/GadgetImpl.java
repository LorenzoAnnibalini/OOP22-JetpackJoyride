package it.unibo.jetpackjoyride.model.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.Gadget;

/**
 * Class to modelize the Gadget information.
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class GadgetImpl implements Gadget{

    private static Map<String, Pair<Boolean, Boolean>> gadget = new HashMap<>();

    @Override
    public Map<String, Pair<Boolean, Boolean>> getAll() {
        return gadget;
    }

    @Override
    public Pair<Boolean, Boolean> getValue(String name) {
        return gadget.get(name);
    }

    @Override
    public void setValue(String name, Boolean state, Boolean purchased) {
        gadget.replace(name, new Pair<Boolean, Boolean>(state, purchased));
    }

    /**
     * Method to set all the values of the Gadget.
     * 
     * @param gadgetMap the map of names and values to set
     */
    public static void setAll(Map<String, Pair<Boolean, Boolean>> gadgetMap) {
        gadget.putAll(gadgetMap);
    }
}
