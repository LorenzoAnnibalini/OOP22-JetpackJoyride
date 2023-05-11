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

import it.unibo.jetpackjoyride.core.api.GadgetLoader;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.core.api.GadgetInfoPositions;

public class GadgetLoaderImpl implements GadgetLoader{

    private final String SEPARATOR = File.separator;
    private final int NAME = 0;
    String filename = "resources" + this.SEPARATOR + "gadget.csv";

    @Override
    public Map<String, List<String>> downloadGadget() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        Map<String, List<String>> gadgetMap = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String name = line.split(";")[NAME];
            line = line.substring(line.indexOf(";") + 1);
            gadgetMap.put(name, 
                new ArrayList<>(List.of(
                    line.split(";")[GadgetInfoPositions.STATE.ordinal()],
                    line.split(";")[GadgetInfoPositions.PURCHASED.ordinal()],
                    line.split(";")[GadgetInfoPositions.PRICE.ordinal()],
                    line.split(";")[GadgetInfoPositions.DESCRIPTION.ordinal()])));
        }
        GadgetImpl.setAll(gadgetMap);
        return gadgetMap;
    }

    @Override
    public void uploadGadget(Map<String, List<String>> gadgetMap) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (String name : gadgetMap.keySet()) {
            writer.write(name + ";" + 
                gadgetMap.get(name).get(GadgetInfoPositions.STATE.ordinal()) + ";" + 
                gadgetMap.get(name).get(GadgetInfoPositions.PURCHASED.ordinal()) + ";" + 
                gadgetMap.get(name).get(GadgetInfoPositions.PRICE.ordinal()) + ";" + 
                gadgetMap.get(name).get(GadgetInfoPositions.DESCRIPTION.ordinal()) + "\n");
        }
        writer.close();
    }

}
