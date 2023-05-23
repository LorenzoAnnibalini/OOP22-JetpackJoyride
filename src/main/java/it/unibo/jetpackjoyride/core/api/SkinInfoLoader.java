package it.unibo.jetpackjoyride.core.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface for classes to load and write skins from file.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public interface SkinInfoLoader {
    /**
     * Method to get the value from file and save them into HashMap in class
     * Skin.
     * 
     * @throws FileNotFoundException if the file is not found
     * @return a map of skins
     */
    Map<String, List<String>> downloadSkin() throws FileNotFoundException;

    /**
     * Method to save new skin information in file
     * 
     * @param skinMap the map to get value that has to be saved
     * @throws IOException
     */
    void uploadSkin(Map<String, List<String>> skinMap) throws IOException;
}
