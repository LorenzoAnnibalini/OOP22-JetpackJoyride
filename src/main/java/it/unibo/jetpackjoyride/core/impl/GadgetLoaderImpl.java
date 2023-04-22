package it.unibo.jetpackjoyride.core.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.core.api.GadgetLoader;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;

public class GadgetLoaderImpl implements GadgetLoader{

    private final String SEPARATOR = File.separator;
    private final int NAME = 0;
    private final int STATE = 1;
    private final int PURCHASED = 2;
    String filename = "resources" + this.SEPARATOR + "gadget.csv";

    @Override
    public Map<String, Pair<Boolean, Boolean>> downloadSaves() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        Map<String, Pair<Boolean, Boolean>> gadgetMap = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            gadgetMap.put(line.split(";")[NAME], new Pair<>(Boolean.parseBoolean(line.split(";")[STATE]), Boolean.parseBoolean(line.split(";")[PURCHASED])));
        }
        GadgetImpl.setAll(gadgetMap);
        return gadgetMap;
    }

    @Override
    public void uploadSaves(Map<String, Pair<Boolean, Boolean>> gadgetMap) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (String name : gadgetMap.keySet()) {
            writer.write(name + ";" + gadgetMap.get(name).getX() + ";" + gadgetMap.get(name).getY() + "\n");
        }
        writer.close();
    }

}
