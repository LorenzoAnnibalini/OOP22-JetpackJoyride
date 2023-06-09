package it.unibo.jetpackjoyride.model.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

import it.unibo.jetpackjoyride.model.api.Saves;

/**
 * Class to save statistics on file.
 */
public final class SavesImpl implements Saves {

    private final Preferences prefs;

    /**
     * Constructor.
     */
    public SavesImpl() {
        this.prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    @Override
    public Map<String, Integer> downloadSaves() throws IOException {
        final Map<String, Integer> stats = new HashMap<>();
        stats.put(StatisticsImpl.MAX_MONEY, prefs.getInt(StatisticsImpl.MAX_MONEY, 0));
        stats.put(StatisticsImpl.MAX_METERS, prefs.getInt(StatisticsImpl.MAX_METERS, 0));
        stats.put(StatisticsImpl.MONEY_SPENT, prefs.getInt(StatisticsImpl.MONEY_SPENT, 0));
        stats.put(StatisticsImpl.KILLED_NPC, prefs.getInt(StatisticsImpl.KILLED_NPC, 0));
        stats.put(StatisticsImpl.DEATHS, prefs.getInt(StatisticsImpl.DEATHS, 0));
        stats.put(StatisticsImpl.GRABBED_OBJECTS, prefs.getInt(StatisticsImpl.GRABBED_OBJECTS, 0));
        stats.put(StatisticsImpl.GRABBED_MONEY, prefs.getInt(StatisticsImpl.GRABBED_MONEY, 0));
        stats.put(StatisticsImpl.TOTAL_METERS, prefs.getInt(StatisticsImpl.TOTAL_METERS, 0));
        stats.put(StatisticsImpl.ACTUAL_MONEY, prefs.getInt(StatisticsImpl.ACTUAL_MONEY, 0));
        return stats;
    }

    @Override
    public void uploadSaves(final Map<String, Integer> stats) throws IOException {
        prefs.putInt(StatisticsImpl.MAX_MONEY, stats.get(StatisticsImpl.MAX_MONEY));
        prefs.putInt(StatisticsImpl.MAX_METERS, stats.get(StatisticsImpl.MAX_METERS));
        prefs.putInt(StatisticsImpl.MONEY_SPENT, stats.get(StatisticsImpl.MONEY_SPENT));
        prefs.putInt(StatisticsImpl.KILLED_NPC, stats.get(StatisticsImpl.KILLED_NPC));
        prefs.putInt(StatisticsImpl.DEATHS, stats.get(StatisticsImpl.DEATHS));
        prefs.putInt(StatisticsImpl.GRABBED_OBJECTS, stats.get(StatisticsImpl.GRABBED_OBJECTS));
        prefs.putInt(StatisticsImpl.GRABBED_MONEY, stats.get(StatisticsImpl.GRABBED_MONEY));
        prefs.putInt(StatisticsImpl.TOTAL_METERS, stats.get(StatisticsImpl.TOTAL_METERS));
        prefs.putInt(StatisticsImpl.ACTUAL_MONEY, stats.get(StatisticsImpl.ACTUAL_MONEY));
    }

}
