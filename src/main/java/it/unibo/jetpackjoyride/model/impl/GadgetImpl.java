package it.unibo.jetpackjoyride.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.jetpackjoyride.model.api.Gadget;

/**
 * Class to modelize the Gadget information.
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class GadgetImpl implements Gadget{

    private static Map<String, List<String>> gadget = new HashMap<>();

    @Override
    public Map<String, List<String>> getAll() {
        return gadget;
    }

    @Override
    public List<String> getValue(String name) {
        return gadget.get(name);
    }

    @Override
    public void setValue(String name, String state, String purchased, 
        String price, String description) {
        gadget.replace(name, 
            new ArrayList<>(List.of(state, purchased, price, description)));
    }

    /**
     * Method to set all the values of the Gadget.
     * 
     * @param gadgetMap the map of names and values to set
     */
    public static void setAll(Map<String, List<String>> gadgetMap) {
        gadget.putAll(gadgetMap);
    }
}
