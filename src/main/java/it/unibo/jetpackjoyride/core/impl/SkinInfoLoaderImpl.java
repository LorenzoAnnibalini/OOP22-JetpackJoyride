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
        try (Scanner sc = new Scanner(new File(filename), StandardCharsets.UTF_8.name())) {
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
        } catch (FileNotFoundException e) {
            throw new  IllegalStateException("skin.csv file not found", e);
        }
        SkinInfoImpl.setAll(skinMap);
        return skinMap;
    }

    @Override
    public final void uploadSkin(final Map<String, List<String>> skinMap) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, StandardCharsets.UTF_8))) {
            for (final Map.Entry<String, List<String>> entry : skinMap.entrySet()) {
                writer.write(entry.getKey() + ";"
                        + entry.getValue().get(SkinInfoPositions.STATE.ordinal()) + ";"
                        + entry.getValue().get(SkinInfoPositions.PURCHASED.ordinal()) + ";"
                        + entry.getValue().get(SkinInfoPositions.PRICE.ordinal()) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new  IllegalStateException("skin.csv file not found", e);
        }
    }

}
