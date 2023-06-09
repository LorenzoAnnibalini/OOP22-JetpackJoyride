package it.unibo.jetpackjoyride.core;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.core.api.GadgetLoader;
import it.unibo.jetpackjoyride.core.api.SkinInfoLoader;
import it.unibo.jetpackjoyride.core.impl.GadgetLoaderImpl;
import it.unibo.jetpackjoyride.core.impl.SkinInfoLoaderImpl;
import it.unibo.jetpackjoyride.model.api.Gadget;
import it.unibo.jetpackjoyride.model.api.SkinInfo;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.model.impl.SkinInfoImpl;

/**
 * JUnit test to test the correct download and upload of skins and gadget.
 */
class DownloadUploadSkinGadgetTest {

    private final GadgetLoader gadgetLoader = new GadgetLoaderImpl();
    private final SkinInfoLoader skinInfoLoader = new SkinInfoLoaderImpl();
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    @Test
    void testUploadDownloadGadget() throws Exception {
        /*actualValues ​​is a temporary class to keep game info 
        * that will be reloaded at the end of the test */
        Map<String, List<String>> actualValues;
        actualValues = gadgetLoader.downloadGadget();
        final Map<String, List<String>> gadgetMap = new HashMap<>();
        gadgetMap.put("Air Barry",
                List.of(FALSE, FALSE, "100", "Moltiplicatore di salto iniziale"));
        gadgetMap.put("Gravity Belt",
                List.of(FALSE, FALSE, "150", "Aumento gravita'"));
        gadgetLoader.uploadGadget(gadgetMap);

        final Gadget gadgets = new GadgetImpl();
        gadgetLoader.downloadGadget();
        assertEquals("the map read from file is not"
                + "equals to the map written in the file", gadgetMap, gadgets.getAll());
        gadgetLoader.uploadGadget(actualValues);
    }

    @Test
    void testUploadDownloadSkin() throws Exception {
        /*actualValues ​​is a temporary class to keep game info 
        * that will be reloaded at the end of the test */
        Map<String, List<String>> actualValues;
        actualValues = skinInfoLoader.downloadSkin();
        final Map<String, List<String>> skinInfoMap = new HashMap<>();
        skinInfoMap.put("barry",
                List.of(TRUE, TRUE, "0"));
        skinInfoMap.put("barryWoman",
                List.of(FALSE, FALSE, "100"));
        skinInfoLoader.uploadSkin(skinInfoMap);

        final SkinInfo skinsInfo = new SkinInfoImpl();
        skinInfoLoader.downloadSkin();
        assertEquals("the map read from file is not "
                + "equals to the map written in the file", skinInfoMap, skinsInfo.getAll());
        skinInfoLoader.uploadSkin(actualValues);
    }
}
