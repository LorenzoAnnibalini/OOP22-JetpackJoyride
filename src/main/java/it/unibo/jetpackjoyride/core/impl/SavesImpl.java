package it.unibo.jetpackjoyride.core.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
        try (Scanner scanner = new Scanner(new File(filename), "UTF-8")) {
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                stats.put(line.split(";")[SavesImpl.NAME], Integer.parseInt(line.split(";")[SavesImpl.VALUE]));
            }
            this.statistics.setAll(stats);
        } catch (IOException e) {
            throw new IllegalStateException("Error while reading statistics from file", e);
        }
        return stats;
    }

    @Override
    public void uploadSaves(final Map<String, Integer> stats) throws IOException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filename), "UTF-8");) {
            for (final Entry<String, Integer> value : stats.entrySet()) {
                writer.write(value.getKey() + ";" + value.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new IllegalStateException("Error while writing statistics on file", e);
        }
    }

}
