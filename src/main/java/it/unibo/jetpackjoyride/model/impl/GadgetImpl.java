package it.unibo.jetpackjoyride.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.jetpackjoyride.model.api.Gadget;
import it.unibo.jetpackjoyride.model.api.GadgetInfoPositions;

/**
 * Class to modelize the Gadget information.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public final class GadgetImpl implements Gadget {

    private static Map<String, List<String>> gadget = new HashMap<>();

    @Override
    public Map<String, List<String>> getAll() {
        return new HashMap<>(gadget);
    }

    @Override
    public List<String> getValue(final String name) {
        return new ArrayList<>(gadget.get(name));
    }

    @Override
    public void setValue(final String name, final String state, final String purchased,
            final String price, final String description) {
        gadget.replace(name,
                new ArrayList<>(List.of(state, purchased, price, description)));
    }

    @Override
    public void setValue(final String name, final List<String> value) {
        final String state = value.get(GadgetInfoPositions.STATE.ordinal());
        final String purchased = value.get(GadgetInfoPositions.PURCHASED.ordinal());
        final String price = value.get(GadgetInfoPositions.PRICE.ordinal());
        final String description = value.get(GadgetInfoPositions.DESCRIPTION.ordinal());
        this.setValue(name, state, purchased, price, description);
    }

    /**
     * Method to set all the values of the Gadget.
     * 
     * @param gadgetMap the map of names and values to set
     */
    public static void setAll(final Map<String, List<String>> gadgetMap) {
        gadget.putAll(gadgetMap);
    }
}
