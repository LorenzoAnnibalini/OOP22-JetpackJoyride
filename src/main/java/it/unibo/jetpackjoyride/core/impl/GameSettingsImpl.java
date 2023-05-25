package it.unibo.jetpackjoyride.core.impl;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.jetpackjoyride.core.api.GameSettings;

/**
 * Implementation of the game settings.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class GameSettingsImpl implements GameSettings {

    private static Map<String, String> settings = new HashMap<>();

    /**
     * Constructor of the class
     * Read the file and put the values in the map
     * 
     * @throws FileNotFoundException if the file is not found
     */
    public GameSettingsImpl() {
        try {
            ReadWriteFile<String, String> reader = new ReadWriteFile<String, String>("gamesettings.csv");
            settings.putAll(reader.readMap());
        } catch (Exception e) {
            System.out.println("Reading Game Settings : ERROR");
            System.out.println(e.toString());
        }
        if (settings.isEmpty()) {
            System.out.println("Reading Game Settings : Empty File -> Default Settings");
            settings.put("audio", "Audio OFF");
            settings.put("difficulty", "Difficult : EASY");
        }
    }

    @Override
    public Map<String, String> getAllSettings() {
        return settings;
    }

    @Override
    public String getValue(String name) {
        return settings.get(name);
    }

    @Override
    public void setValue(String name, String value) {
        settings.replace(name, value);
    }

    @Override
    public void writeSettings() {
        try {
            ReadWriteFile<String, String> reader = new ReadWriteFile<String, String>("gamesettings.csv");
            reader.writeMap(settings);
        } catch (Exception e) {
            System.out.println("Writing Game Settings : Error");
            System.out.println(e.toString());
        }
    }

}
