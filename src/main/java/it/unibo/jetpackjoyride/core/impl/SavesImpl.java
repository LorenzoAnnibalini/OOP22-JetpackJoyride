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
    String filename = "saves.csv";
    private Statistics statistics = new StatisticsImpl();

    @Override
    public Map<String, Integer> downloadSaves() throws IOException {
        Map<String, Integer> stats = new HashMap<>();
        ReadWriteFile<String, Integer> reader = new ReadWriteFile<>(filename);
        this.statistics.setAll(reader.readMap());
        return stats;
    }

    @Override
    public void uploadSaves(Map<String, Integer> stats) throws IOException {
        ReadWriteFile<String, Integer> writer = new ReadWriteFile<>(filename);
        writer.writeMap(stats);
    }

}
