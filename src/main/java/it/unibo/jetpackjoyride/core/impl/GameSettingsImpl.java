package it.unibo.jetpackjoyride.core.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import it.unibo.jetpackjoyride.core.api.GameSettings;

/**
 * Implementation of the game settings.
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class GameSettingsImpl implements GameSettings {

    private static Map<String, String> settings = new HashMap<>();
    

    /**
     * Constructor of the class
     * Read the file and put the values in the map
     * @throws FileNotFoundException if the file is not found
     */
    public GameSettingsImpl() {
        try{
            ReadWriteFile<String,String> reader = new ReadWriteFile("game_settings.csv");
            settings.putAll(reader.readMap());
        }catch(Exception e){
            System.out.println("Reading Game Settings : File Not Found");
            System.out.println(e.toString());
        }
        if(settings.isEmpty()){
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
    public void writeSettings(){
        FileWriter csvWriter;
        try{
            ReadWriteFile<String,String> reader = new ReadWriteFile("game_settings.csv");
            reader.writeMap(settings);
        }catch(Exception e){
            System.out.println("Writing Game Settings : Error");
            System.out.println(e.toString());
        }
    }

    
    
}
