package it.unibo.jetpackjoyride.core.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.jetpackjoyride.core.api.GameSettings;

/**
 * Implementation of the game settings.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public final class GameSettingsImpl implements GameSettings {

    private static Map<String, String> settings = new HashMap<>();

    /**
     * Constructor of the class.
     * Read the file and put the values in the map.
     * 
     * @throws FileNotFoundException if the file is not found
     */
    public GameSettingsImpl() throws FileNotFoundException { 
            final ReadWriteFile<String, String> reader = new ReadWriteFile<>("gamesettings.csv");
            settings.putAll(reader.readMap());
        if (settings.isEmpty()) {
            settings.put("audio", "Audio OFF");
            settings.put("difficulty", "Difficult : EASY");
        }
    }

    @Override
    public Map<String, String> getAllSettings() {
        return settings;
    }

    @Override
    public String getValue(final String name) {
        return settings.get(name);
    }

    @Override
    public void setValue(final String name, final String value) {
        settings.replace(name, value);
    }

    @Override
    public void writeSettings() throws IOException {
            final ReadWriteFile<String, String> reader = new ReadWriteFile<>("gamesettings.csv");
            reader.writeMap(settings);
    }

}
