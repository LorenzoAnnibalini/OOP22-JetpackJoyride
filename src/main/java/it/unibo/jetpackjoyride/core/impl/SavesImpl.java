package it.unibo.jetpackjoyride.core.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

public class SavesImpl implements Saves {

    // private final String SEPARATOR = File.separator;
    private final static int NAME = 0;
    private final static int VALUE = 1;
    private final String SEPARATOR = File.separator;
    String filename = "src" + this.SEPARATOR +
            "main" + this.SEPARATOR +
            "resources" + this.SEPARATOR +
            "saves.csv";
    private Statistics statistics = new StatisticsImpl();

    @Override
    public Map<String, Integer> downloadSaves() throws IOException {
        Scanner sc = new Scanner(new File(filename));
        Map<String, Integer> stats = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            stats.put(line.split(";")[SavesImpl.NAME], Integer.parseInt(line.split(";")[SavesImpl.VALUE]));
        }
        this.statistics.setAll(stats);
        sc.close();
        return stats;
    }

    @Override
    public void uploadSaves(Map<String, Integer> stats) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (String name : stats.keySet()) {
                writer.write(name + ";" + stats.get(name) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
