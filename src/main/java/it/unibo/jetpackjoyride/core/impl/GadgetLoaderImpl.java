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
    private final int STATE = 1;
    private final int PURCHASED = 2;
    private final int PRICE = 3;
    private final int DESCRIPTION = 4;
    String filename = "resources" + this.SEPARATOR + "gadget.csv";

    @Override
    public Map<String, List<String>> downloadGadget() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        Map<String, List<String>> gadgetMap = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            gadgetMap.put(line.split(";")[NAME], 
                new ArrayList<>(List.of(
                    line.split(";")[STATE],
                    line.split(";")[PURCHASED],
                    line.split(";")[PRICE],
                    line.split(";")[DESCRIPTION])));
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
