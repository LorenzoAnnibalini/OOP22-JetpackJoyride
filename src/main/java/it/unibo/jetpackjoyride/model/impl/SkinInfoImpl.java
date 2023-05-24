package it.unibo.jetpackjoyride.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.jetpackjoyride.core.api.SkinInfoPositions;
import it.unibo.jetpackjoyride.model.api.SkinInfo;

/**
 * Class to model a skin info.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class SkinInfoImpl implements SkinInfo {

    private static Map<String, List<String>> skin = new HashMap<>();

    @Override
    public final Map<String, List<String>> getAll() {
        return new HashMap<>(skin);
    }

    @Override
    public final List<String> getValue(final String name) {
        return new ArrayList<>(skin.get(name));
    }

    @Override
    public final void setValue(final String name, final String state, final String purchased, final String price) {
        skin.replace(name,
                new ArrayList<>(List.of(state, purchased, price)));
    }

    @Override
    public final void setValue(final String name, final List<String> value) {
        final String state = value.get(SkinInfoPositions.STATE.ordinal());
        final String purchased = value.get(SkinInfoPositions.PURCHASED.ordinal());
        final String price = value.get(SkinInfoPositions.PRICE.ordinal());
        this.setValue(name, state, purchased, price);
    }

    /**
     * Method to set all the values of the Skin.
     * 
     * @param skinMap the map of names and values to set
     */
    public static void setAll(final Map<String, List<String>> skinMap) {
        skin.putAll(skinMap);
    }
}
