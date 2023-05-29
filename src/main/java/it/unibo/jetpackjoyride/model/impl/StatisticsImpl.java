package it.unibo.jetpackjoyride.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.Statistics;

/**
 * Class to modelize the statistics of the game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public final class StatisticsImpl implements Statistics {
    /**
     * Constants for max money grabbed and value to use in the view.
     */
    public static final Pair<String, String> MAX_MONEY = new Pair<>("MaxMoney", "Max Money");
    /**
     * Constants for max meters traveled and value to use in the view.
     */
    public static final Pair<String, String> MAX_METERS = new Pair<>("MaxMeters", "Max Distance");
    /**
     * Constants for max score and value to use in the view.
     */
    public static final Pair<String, String> MONEY_SPENT = new Pair<>("MoneySpent", "Money Spent");
    /**
     * Constants for Killed Npc and value to use in the view.
     */
    public static final Pair<String, String> KILLED_NPC = new Pair<>("KilledNpc", "Killd Npc");
    /**
     * Constants for deaths and value to use in the view.
     */
    public static final Pair<String, String> DEATHS = new Pair<>("Deaths", "Deaths");
    /**
     * Constants for grabbed objects and value to use in the view.
     */
    public static final Pair<String, String> GRABBED_OBJECTS = new Pair<>("GrabbedObjects", "Grabbed Objects");
    /**
     * Constants for grabbed money and value to use in the view.
     */
    public static final Pair<String, String> GRABBED_MONEY = new Pair<>("GrabbedMoney", "Grabbed Money");
    /**
     * Constants for total meters traveled and value to use in the view.
     */
    public static final Pair<String, String> TOTAL_METERS = new Pair<>("TotalMeters", "Total Distance");
    /**
     * Constants for actual money and value to use in the view.
     */
    public static final Pair<String, String> ACTUAL_MONEY = new Pair<>("ActualMoney", "Actual Money");

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
        if (runStats.get(GRABBED_MONEY.getX()) > statistics.get(MAX_MONEY.getX())) {
            this.setValue(MAX_MONEY.getX(), runStats.get(GRABBED_MONEY.getX()));
        }
        if (runStats.get(TOTAL_METERS.getX()) > statistics.get(MAX_METERS.getX())) {
            this.setValue(MAX_METERS.getX(), runStats.get(TOTAL_METERS.getX()));
        }
        this.increment(ACTUAL_MONEY.getX(), runStats.get(GRABBED_MONEY.getX()));
    }

}
