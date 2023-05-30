package it.unibo.jetpackjoyride.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import it.unibo.jetpackjoyride.model.api.Statistics;

/**
 * Class to modelize the statistics of the game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public final class StatisticsImpl implements Statistics {
    /**
     * Constants for max money name.
     */
    public static final String MAX_MONEY = "Max Money";
    /**
     * Constants for max meters name.
     */

    public static final String MAX_METERS = "Max Meters";
    /**
     * Constants for actual money name.
     */
    public static final String ACTUAL_MONEY = "Actual Money";
    /**
     * Constants for grabbed money name.
     */
    public static final String GRABBED_MONEY = "Grabbed Money";
    /**
     * Constants for total meters name.
     */
    public static final String TOTAL_METERS = "Total Meters";
    /**
     * Constants for deaths name.
     */
    public static final String DEATHS = "Deaths";
    /**
     * Constants for killedNpc name.
     */
    public static final String KILLED_NPC = "Killed NPC";
    /**
     * Constants for money spent name.
     */
    public static final String MONEY_SPENT = "Money Spent";
    /**
     * Constants for grabbed objects name.
     */
    public static final String GRABBED_OBJECTS = "Grabbed Objects";

    private final Map<String, Integer> statistics = new HashMap<>();

    @Override
    public int getValue(final String name) {
        return this.statistics.get(name);
    }

    @Override
    public void setValue(final String name, final int value) {
        this.statistics.replace(name, value);
    }

    @Override
    public void increment(final String name, final int value) {
        this.statistics.replace(name, this.statistics.get(name) + value);
    }

    @Override
    public void increment(final String name) {
        this.statistics.replace(name, this.statistics.get(name) + 1);
    }

    @Override
    public Map<String, Integer> getAll() {
        return new HashMap<>(this.statistics);
    }

    @Override
    public void addStatistic(final String name, final int value) {
        this.statistics.put(name, value);
    }

    @Override
    public void setAll(final Map<String, Integer> stats) {
        this.statistics.putAll(stats);
    }

    @Override
    public void updateGeneralStats(final Map<String, Integer> runStats) {
        for (final Entry<String, Integer> entry : runStats.entrySet()) {
            this.increment(entry.getKey(), entry.getValue());
        }
        if (runStats.get(GRABBED_MONEY) > statistics.get(MAX_MONEY)) {
            this.setValue(MAX_MONEY, runStats.get(GRABBED_MONEY));
        }
        if (runStats.get(TOTAL_METERS) > statistics.get(MAX_METERS)) {
            this.setValue(MAX_METERS, runStats.get(TOTAL_METERS));
        }
        this.increment(ACTUAL_MONEY, runStats.get(GRABBED_MONEY));
    }

}
