package it.unibo.jetpackjoyride.core.api;

import java.io.IOException;
import java.util.Map;

/**
 * Interface for game settings.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public interface GameSettings {

    /**
     * Method to get map of settings
     * 
     * @return map of settings
     */
    Map<String, String> getAllSettings();

    /**
     * Getter of a setting
     * 
     * @param name tha name of the setting to get the value
     * @return value of the setting
     */
    String getValue(String name);

    /**
     * Setter for a setting
     * 
     * @param name  the name of the setting that want to be set
     * @param value the new value of the setting
     */
    void setValue(String name, String value);

    /**
     * Method to write the settings in a file
     * 
     * @throws IOException
     * @throws FileNotFoundException if the file is not found
     */
    void writeSettings() throws IOException;

}
