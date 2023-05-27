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
import it.unibo.jetpackjoyride.core.api.SkinInfoLoader;
import it.unibo.jetpackjoyride.model.impl.SkinInfoImpl;

/**
 * Class to load and write skins information from file.
 */
public class SkinInfoLoaderImpl implements SkinInfoLoader {

    private static final String SEPARATOR = File.separator;
    private static final int NAME = 0;
    private final String filename = "src" + SEPARATOR
            + "main" + SEPARATOR
            + "resources" + SEPARATOR
            + "skin.csv";

    @Override
    public final Map<String, List<String>> downloadSkin() throws FileNotFoundException {
        final Map<String, List<String>> skinMap = new HashMap<>();
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                final String name = line.split(";")[NAME];
                line = line.substring(line.indexOf(';') + 1);
                skinMap.put(name,
                        new ArrayList<>(List.of(
                                line.split(";")[SkinInfoPositions.STATE.ordinal()],
                                line.split(";")[SkinInfoPositions.PURCHASED.ordinal()],
                                line.split(";")[SkinInfoPositions.PRICE.ordinal()])));
            }
            sc.close();
        }
        SkinInfoImpl.setAll(skinMap);
        return skinMap;
    }

    @Override
    public final void uploadSkin(final Map<String, List<String>> skinMap) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (final String name : skinMap.keySet()) {
                writer.write(name + ";"
                        + skinMap.get(name).get(SkinInfoPositions.STATE.ordinal()) + ";"
                        + skinMap.get(name).get(SkinInfoPositions.PURCHASED.ordinal()) + ";"
                        + skinMap.get(name).get(SkinInfoPositions.PRICE.ordinal()) + "\n");
            }
            writer.close();
        }
    }

}
