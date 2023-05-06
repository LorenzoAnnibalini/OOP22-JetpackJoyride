package it.unibo.jetpackjoyride.core.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import it.unibo.jetpackjoyride.core.api.SkinInfoPositions;
import it.unibo.jetpackjoyride.core.api.SkinLoader;
import it.unibo.jetpackjoyride.model.impl.SkinImpl;

public class SkinLoaderImpl implements SkinLoader{
    
    private final String SEPARATOR = File.separator;
    private final int NAME = 0;
    String filename = "resources" + this.SEPARATOR + "skin.csv";

    @Override
    public Map<String, List<String>> downloadSkin() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        Map<String, List<String>> skinMap = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String name = line.split(";")[NAME];
            line = line.substring(line.indexOf(";") + 1);
            skinMap.put(name, 
                new ArrayList<>(List.of(
                    line.split(";")[SkinInfoPositions.STATE.ordinal()],
                    line.split(";")[SkinInfoPositions.PURCHASED.ordinal()])));
        }
        SkinImpl.setAll(skinMap);
        return skinMap;
    }

    @Override
    public void uploadSkin(Map<String, List<String>> skinMap) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (String name : skinMap.keySet()) {
            writer.write(name + ";" + 
                skinMap.get(name).get(SkinInfoPositions.STATE.ordinal()) + ";" + 
                skinMap.get(name).get(SkinInfoPositions.PURCHASED.ordinal()) + "\n");
        }
        writer.close();
    }
    
}
