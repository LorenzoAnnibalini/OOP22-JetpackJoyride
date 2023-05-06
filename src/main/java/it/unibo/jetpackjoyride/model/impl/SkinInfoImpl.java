package it.unibo.jetpackjoyride.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.jetpackjoyride.model.api.SkinInfo;

public class SkinInfoImpl implements SkinInfo{

    private static Map<String, List<String>> skin = new HashMap<>();

    @Override
    public Map<String, List<String>> getAll() {
        return skin;
    }

    @Override
    public List<String> getValue(String name) {
        return skin.get(name);
    }

    @Override
    public void setValue(String name, String state, String purchased) {
        skin.replace(name, 
            new ArrayList<>(List.of(state, purchased)));
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
