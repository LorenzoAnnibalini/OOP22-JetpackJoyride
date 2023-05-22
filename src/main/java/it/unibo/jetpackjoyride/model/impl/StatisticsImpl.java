package it.unibo.jetpackjoyride.model.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.Statistics;

/**
 * Class to modelize the statistics of the game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class StatisticsImpl implements Statistics {

    public static final Pair<String, String> MAX_MONEY = new Pair<>("MaxMoney", "Max Money");
    public static final Pair<String, String> MAX_METERS = new Pair<>("MaxMeters", "Max Distance");
    public static final Pair<String, String> MONEY_SPENT = new Pair<>("MoneySpent", "Money Spent");
    public static final Pair<String, String> KILLED_NPC = new Pair<>("KilledNpc", "Killd Npc");
    public static final Pair<String, String> DEATHS = new Pair<>("Deaths", "Deaths");
    public static final Pair<String, String> GRABBED_OBJECTS = new Pair<>("GrabbedObjects", "Grabbed Objects");
    public static final Pair<String, String> GRABBED_MONEY = new Pair<>("GrabbedMoney", "Grabbed Money");
    public static final Pair<String, String> TOTAL_METERS = new Pair<>("TotalMeters", "Total Distance");
    public static final Pair<String, String> ACTUAL_MONEY = new Pair<>("ActualMoney", "Actual Money");



    private Map<String, Integer> statistics = new HashMap<>();

    @Override
    public int getValue(String name) {
        return this.statistics.get(name);
    }

    @Override
    public void setValue(String name, int value) {
        this.statistics.replace(name, value);
    }

    @Override
    public void increment(String name, int value) {
        this.statistics.replace(name, this.statistics.get(name) + value);
    }

    @Override
    public void increment(String name) {
        this.statistics.replace(name, this.statistics.get(name) + 1);
    }

    @Override
    public Map<String, Integer> getAll() {
        return this.statistics;
    }

    @Override
    public void addStatistic(String name, int value) {
        this.statistics.put(name, value);
    }

    @Override
    public void setAll(Map<String, Integer> stats) {
        this.statistics.putAll(stats);
    }

    @Override
    public void updateGeneralStats(Map<String, Integer> runStats) {
        for (String key : runStats.keySet()) {
            this.increment(key, runStats.get(key));
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
