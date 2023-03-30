package it.unibo.jetpackjoyride.model.impl;

import java.util.HashMap;
import java.util.Map;
import it.unibo.jetpackjoyride.model.api.Statistics;

/**
 * Class to modelize the statisstics of the game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class StatisticsImpl implements Statistics {

    private static Map<String, Integer> statistics = new HashMap<>();

    @Override
    public int getValue(String name) {
        return statistics.get(statistics.keySet().stream()
                .filter(x -> x.startsWith(name))
                .findAny()
                .get());
    }

    @Override
    public void setValue(String name, int value) {
        statistics.replace(name, value);
    }

    @Override
    public void increment(String name, int value) {
        statistics.replace(name, statistics.get(name) + value);
    }

    @Override
    public void increment(String name) {
        statistics.replace(name, statistics.get(name) + 1);
    }

    @Override
    public Map<String, Integer> getAll() {
        return statistics;
    }

    /**
     * Method to set all the values of the statistics.
     * 
     * @param stats the map of names and values to set
     */
    public static void setAll(Map<String, Integer> stats) {
        statistics.putAll(stats);
    }

}
