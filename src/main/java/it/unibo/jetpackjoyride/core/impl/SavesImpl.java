package it.unibo.jetpackjoyride.core.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
    private final String filename = "/saves.csv";
    private Statistics statistics = new StatisticsImpl();

    @Override
    public Map<String, Integer> downloadSaves() throws IOException {
        final InputStream stream = this.getClass().getResourceAsStream(filename);
        String fileContent = new String(stream.readAllBytes(),
                StandardCharsets.UTF_8);
        stream.close();
        Scanner sc = new Scanner(fileContent);
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
        try{
            PrintWriter writer = new PrintWriter(
                new File(this.getClass().getResource(filename).getPath()));
            for (String name : stats.keySet()) {
                writer.write(name + ";" + stats.get(name) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
