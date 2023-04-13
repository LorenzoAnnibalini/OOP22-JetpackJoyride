package it.unibo.jetpackjoyride.core.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import it.unibo.jetpackjoyride.core.api.GadgetLoader;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;

public class GadgetLoaderImpl implements GadgetLoader{

    private final String SEPARATOR = File.separator;
    private final int NAME = 0;
    private final int STATE = 1;
    String filename = "resources" + this.SEPARATOR + "gadget.csv";

    @Override
    public Map<String, Boolean> downloadSaves() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        Map<String, Boolean> gadgetMap = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            gadgetMap.put(line.split(";")[NAME], Boolean.parseBoolean(line.split(";")[STATE]));
        }
        GadgetImpl.setAll(gadgetMap);
        return gadgetMap;
    }

    @Override
    public void uploadSaves(Map<String, Boolean> gadgetMap) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (String name : gadgetMap.keySet()) {
            writer.write(name + ";" + gadgetMap.get(name) + "\n");
        }
        writer.close();
    }

}
