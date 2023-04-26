package it.unibo.jetpackjoyride.core.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import it.unibo.jetpackjoyride.common.Pair;

/**
 * Interface for classes to load and write gadgets from file.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public interface GadgetLoader {
    /**
     * Method to get the value from file and save them into HashMap in class
     * Gadget.
     * 
     * @throws FileNotFoundException if the file is not found
     * @return a map of gadgets
     */
    Map<String, Pair<Boolean, Boolean>> downloadGadget() throws FileNotFoundException;

    /**
     * Method to save new gadgets in file
     * 
     * @param stats the map to get value that has to be save
     * @throws IOException
     */
    void uploadGadget(Map<String, Pair<Boolean, Boolean>> stats) throws IOException;
}
