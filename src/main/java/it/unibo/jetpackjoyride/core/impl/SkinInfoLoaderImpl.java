package it.unibo.jetpackjoyride.core.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import it.unibo.jetpackjoyride.core.api.SkinInfoPositions;
import it.unibo.jetpackjoyride.core.api.SkinInfoLoader;
import it.unibo.jetpackjoyride.model.impl.SkinInfoImpl;

/**
 * Class to load and write skins information from file.
 */
public class SkinInfoLoaderImpl implements SkinInfoLoader {

    /**
     * Costants for BARRY skin name.
     */
    public static final String BARRY = "barry";

    /**
     * Costants for BARRY skin state.
     */
    public static final String BARRY_STATE = "barryState";

    /**
     * Costants for BARRY skin purchased.
     */
    public static final String BARRY_PURCHASED = "barryPurchased";

    /**
     * Costants for BARRY skin price.
     */
    public static final String BARRY_PRICE = "barryPrice";

    /**
     * Costants for BARRY_WOMAN skin name.
     */
    public static final String BARRY_WOMAN = "barryWoman";

    /**
     * Costants for BARRY_WOMAN skin state.
     */
    public static final String BARRY_WOMAN_STATE = "barryWomanState";

    /**
     * Costants for BARRY_WOMAN skin purchased.
     */
    public static final String BARRY_WOMAN_PURCHASED = "barryWomanPurchased";

    /**
     * Costants for BARRY_WOMAN skin price.
     */
    public static final String BARRY_WOMAN_PRICE = "barryWomanPrice";
    private final Preferences prefs;

    /**
     * Constructor of the class SkinInfoImpl.
     */
    public SkinInfoLoaderImpl() {
        this.prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    @Override
    public final Map<String, List<String>> downloadSkin() throws FileNotFoundException {
        final Map<String, List<String>> skinMap = new HashMap<>();
        List<String> info;

        info = List.of(prefs.get(BARRY_STATE, "true"),
                prefs.get(BARRY_PURCHASED, "true"),
                prefs.get(BARRY_PRICE, "0"));
        skinMap.put(BARRY, new ArrayList<>(info));

        info = List.of(prefs.get(BARRY_WOMAN_STATE, "false"),
                prefs.get(BARRY_WOMAN_PURCHASED, "false"),
                prefs.get(BARRY_WOMAN_PRICE, "100"));
        skinMap.put(BARRY_WOMAN, new ArrayList<>(info));

        SkinInfoImpl.setAll(skinMap);
        return skinMap;
    }

    @Override
    public final void uploadSkin(final Map<String, List<String>> skinMap) throws IOException {
        prefs.put(BARRY_STATE, skinMap.get(BARRY).get(SkinInfoPositions.STATE.ordinal()));
        prefs.put(BARRY_PURCHASED, skinMap.get(BARRY).get(SkinInfoPositions.PURCHASED.ordinal()));
        prefs.put(BARRY_PRICE, skinMap.get(BARRY).get(SkinInfoPositions.PRICE.ordinal()));

        prefs.put(BARRY_WOMAN_STATE, skinMap.get(BARRY_WOMAN).get(SkinInfoPositions.STATE.ordinal()));
        prefs.put(BARRY_WOMAN_PURCHASED, skinMap.get(BARRY_WOMAN).get(SkinInfoPositions.PURCHASED.ordinal()));
        prefs.put(BARRY_WOMAN_PRICE, skinMap.get(BARRY_WOMAN).get(SkinInfoPositions.PRICE.ordinal()));
    }

}
