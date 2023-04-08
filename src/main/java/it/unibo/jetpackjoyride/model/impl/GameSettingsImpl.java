package it.unibo.jetpackjoyride.model.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import it.unibo.jetpackjoyride.model.api.GameSettings;

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
    public GameSettingsImpl() throws FileNotFoundException {

        Scanner sc = new Scanner(new File("/Users/lorenzoannibalini/Desktop/TMP/OOP22-JetpackJoyride/resources/game_settings.csv"));  
        sc.useDelimiter(";"); 
        try{ 
            while (sc.hasNext()) { 
                    String key = sc.next();
                    String value = sc.next();  
                    settings.put(key, value);
                    System.out.println("Reading Game Settings : " + key + " " + value);
            }
        }catch(Exception e){
            System.out.println("Reading Game Settings : Error");
        }
        sc.close();  
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
    public void writeSettings() {
        FileWriter csvWriter;
        try{
            csvWriter = new FileWriter("/Users/lorenzoannibalini/Desktop/TMP/OOP22-JetpackJoyride/resources/game_settings.csv");
            for (Map.Entry<String, String> entry : settings.entrySet()) {
                csvWriter.append(entry.getKey());
                csvWriter.append(";");
                csvWriter.append(entry.getValue());
                csvWriter.append(";");
            }
            csvWriter.close();
        }catch(IOException e){
            System.out.println("Writing Game Settings : Error");
        }
    }

    
    
}
