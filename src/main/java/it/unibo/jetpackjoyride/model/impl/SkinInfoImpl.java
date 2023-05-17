package it.unibo.jetpackjoyride.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.jetpackjoyride.core.api.SkinInfoPositions;
import it.unibo.jetpackjoyride.model.api.SkinInfo;

public class SkinInfoImpl implements SkinInfo{

    private static Map<String, List<String>> skin = new HashMap<>();

    @Override
    public Map<String, List<String>> getAll() {
        return new HashMap<>(skin);
    }

    @Override
    public List<String> getValue(String name) {
        return new ArrayList<>(skin.get(name));
    }

    @Override
    public void setValue(String name, String state, String purchased, String price) {
        skin.replace(name, 
            new ArrayList<>(List.of(state, purchased, price)));
    }

    @Override
    public void setValue(String name, List<String> value) {
        String state = value.get(SkinInfoPositions.STATE.ordinal());
        String purchased = value.get(SkinInfoPositions.PURCHASED.ordinal());
        String price = value.get(SkinInfoPositions.PRICE.ordinal());
        this.setValue(name, state, purchased, price);
    }
    
    /**
     * Method to set all the values of the Skin.
     * 
     * @param skinMap the map of names and values to set
     */
    public static void setAll(Map<String, List<String>> skinMap) {
        skin.putAll(skinMap);
    }
}
