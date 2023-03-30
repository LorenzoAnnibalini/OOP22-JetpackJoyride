package it.unibo.jetpackjoyride.core.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

public class SavesImpl implements Saves {

    private final String SEPARATOR = File.separator; 
    private final int NAME = 0;
    private final int VALUE = 1;
    String filename = "resources" + this.SEPARATOR + "saves.csv";

    @Override
    public Map<String, Integer> downloadSaves() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        Map<String, Integer> stats = new HashMap<>();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            stats.put(line.split(";")[NAME], Integer.parseInt(line.split(";")[VALUE]));
        }
        StatisticsImpl.setAll(stats);
        return stats;
    }

    @Override
    public void uploadSaves(Map<String, Integer> stats) {
        
    }
    
}
