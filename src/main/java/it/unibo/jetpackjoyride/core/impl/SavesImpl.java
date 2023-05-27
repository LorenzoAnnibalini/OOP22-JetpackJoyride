package it.unibo.jetpackjoyride.core.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

/**
 * Class to save statistics on file.
 */
public final class SavesImpl implements Saves {

    // private final String SEPARATOR = File.separator;
    private static final int NAME = 0;
    private static final int VALUE = 1;
    private static final String SEPARATOR = File.separator;
    private final String filename = "src" + SEPARATOR
            + "main" + SEPARATOR
            + "resources" + SEPARATOR
            + "saves.csv";
    private final Statistics statistics = new StatisticsImpl();

    @Override
    public Map<String, Integer> downloadSaves() throws IOException {
        final Map<String, Integer> stats = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                stats.put(line.split(";")[SavesImpl.NAME], Integer.parseInt(line.split(";")[SavesImpl.VALUE]));
            }
            this.statistics.setAll(stats);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stats;
    }

    @Override
    public void uploadSaves(final Map<String, Integer> stats) throws IOException {
        //final BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (final String name : stats.keySet()) {
                writer.write(name + ";" + stats.get(name) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

