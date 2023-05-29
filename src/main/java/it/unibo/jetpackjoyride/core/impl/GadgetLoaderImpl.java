package it.unibo.jetpackjoyride.core.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import it.unibo.jetpackjoyride.core.api.GadgetLoader;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.core.api.GadgetInfoPositions;
/**
 * Class to load and write gadgets from file.
 */
public class GadgetLoaderImpl implements GadgetLoader {

    private static final String SEPARATOR = File.separator;
    private static final int NAME = 0;
    private final String filename = "src" + SEPARATOR
            + "main" + SEPARATOR
            + "resources" + SEPARATOR
            + "gadget.csv";

    @Override
    public final Map<String, List<String>> downloadGadget() throws FileNotFoundException {
        final Map<String, List<String>> gadgetMap = new HashMap<>();
        try (Scanner sc = new Scanner(new File(filename), StandardCharsets.UTF_8.name())) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                final String name = line.split(";")[NAME];
                line = line.substring(line.indexOf(';') + 1);
                gadgetMap.put(name,
                        new ArrayList<>(List.of(
                                line.split(";")[GadgetInfoPositions.STATE.ordinal()],
                                line.split(";")[GadgetInfoPositions.PURCHASED.ordinal()],
                                line.split(";")[GadgetInfoPositions.PRICE.ordinal()],
                                line.split(";")[GadgetInfoPositions.DESCRIPTION.ordinal()])));
            }
            sc.close();
        } catch (IOException e) {
            throw new  IllegalStateException("gadget.csv file not found", e);
        }

        GadgetImpl.setAll(gadgetMap);
        return gadgetMap;
    }

    @Override
    public final void uploadGadget(final Map<String, List<String>> gadgetMap) 
        throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, StandardCharsets.UTF_8))) {
            for (final Map.Entry<String, List<String>> entry : gadgetMap.entrySet()) {
                writer.write(entry.getKey() + ";"
                        + entry.getValue().get(GadgetInfoPositions.STATE.ordinal())
                        + ";"
                        + entry.getValue().get(GadgetInfoPositions.PURCHASED.ordinal())
                        + ";"
                        + entry.getValue().get(GadgetInfoPositions.PRICE.ordinal())
                        + ";"
                        + entry.getValue().get(GadgetInfoPositions.DESCRIPTION.ordinal())
                        + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new  IllegalStateException("gadget.csv file not found", e);
        }
    }

}
