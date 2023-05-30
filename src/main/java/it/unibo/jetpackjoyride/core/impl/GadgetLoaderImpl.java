package it.unibo.jetpackjoyride.core.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import it.unibo.jetpackjoyride.core.api.GadgetLoader;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.core.api.GadgetInfoPositions;
/**
 * Class to load and write gadgets from file.
 */
public class GadgetLoaderImpl implements GadgetLoader {

    /**
     * Costants for AIR_BARRY gadget name.
     */
    public static final String AIR_BARRY = "Air Barry";

    /**
     * Costants for AIR_BARRY gadget state.
     */
    public static final String AIR_BARRY_STATE = "airBarryState";

    /**
     * Costants for AIR_BARRY gadget purchased.
     */
    public static final String AIR_BARRY_PURCHASED = "airBarryPurchased";

    /**
     * Costants for AIR_BARRY gadget price.
     */
    public static final String AIR_BARRY_PRICE = "airBarryPrice";

    /**
     * Costants for AIR_BARRY gadget description.
     */
    public static final String AIR_BARRY_DESCRIPTION = "airBarryDescription";

    /**
     * Costants for GRAVITY_BELT gadget name.
     */
    public static final String GRAVITY_BELT = "Gravity Belt";

    /**
     * Costants for GRAVITY_BELT gadget state.
     */
    public static final String GRAVITY_BELT_STATE = "gravityBeltState";

    /**
     * Costants for GRAVITY_BELT gadget purchased.
     */
    public static final String GRAVITY_BELT_PURCHASED = "gravityBeltPurchased";

    /**
     * Costants for GRAVITY_BELT gadget price.
     */
    public static final String GRAVITY_BELT_PRICE = "gravityBeltPrice";

    /**
     * Costants for GRAVITY_BELT gadget description.
     */
    public static final String GRAVITY_BELT_DESCRIPTION = "gravityBeltDescription";

    private static final String FALSE = "false";
    private final Preferences prefs;

    /**
     * Constructor of the class GadgetLoaderImpl.
     */
    public GadgetLoaderImpl() {
        this.prefs = Preferences.userRoot().node(this.getClass().getName());
    }


    @Override
    public final Map<String, List<String>> downloadGadget() throws FileNotFoundException {
        final Map<String, List<String>> gadgetMap = new HashMap<>();

        List<String> info;

        info = List.of(prefs.get(AIR_BARRY_STATE, FALSE),
                prefs.get(AIR_BARRY_PURCHASED, FALSE),
                prefs.get(AIR_BARRY_PRICE, "100"),
                prefs.get(AIR_BARRY_DESCRIPTION, "Moltiplicatore di salita"));
        gadgetMap.put(AIR_BARRY, new ArrayList<>(info));

        info = List.of(prefs.get(GRAVITY_BELT_STATE, FALSE),
                prefs.get(GRAVITY_BELT_PURCHASED, FALSE),
                prefs.get(GRAVITY_BELT_PRICE, "150"),
                prefs.get(GRAVITY_BELT_DESCRIPTION, "Aumento gravita'"));
        gadgetMap.put(GRAVITY_BELT, new ArrayList<>(info));

        GadgetImpl.setAll(gadgetMap);
        return gadgetMap;
    }

    @Override
    public final void uploadGadget(final Map<String, List<String>> gadgetMap) 
        throws IOException {

        prefs.put(AIR_BARRY_STATE, gadgetMap.get(AIR_BARRY).get(GadgetInfoPositions.STATE.ordinal()));
        prefs.put(AIR_BARRY_PURCHASED, gadgetMap.get(AIR_BARRY).get(GadgetInfoPositions.PURCHASED.ordinal()));
        prefs.put(AIR_BARRY_PRICE, gadgetMap.get(AIR_BARRY).get(GadgetInfoPositions.PRICE.ordinal()));
        prefs.put(AIR_BARRY_DESCRIPTION, gadgetMap.get(AIR_BARRY).get(GadgetInfoPositions.DESCRIPTION.ordinal()));

        prefs.put(GRAVITY_BELT_STATE, gadgetMap.get(GRAVITY_BELT).get(GadgetInfoPositions.STATE.ordinal()));
        prefs.put(GRAVITY_BELT_PURCHASED, gadgetMap.get(GRAVITY_BELT).get(GadgetInfoPositions.PURCHASED.ordinal()));
        prefs.put(GRAVITY_BELT_PRICE, gadgetMap.get(GRAVITY_BELT).get(GadgetInfoPositions.PRICE.ordinal()));
        prefs.put(GRAVITY_BELT_DESCRIPTION, gadgetMap.get(GRAVITY_BELT).get(GadgetInfoPositions.DESCRIPTION.ordinal()));
    }

}
