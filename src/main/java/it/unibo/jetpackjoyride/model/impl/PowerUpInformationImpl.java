package it.unibo.jetpackjoyride.model.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.PowerUpInformation;

/**
 * Class to modelize the powerUp information.
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class PowerUpInformationImpl implements PowerUpInformation{

    private Map<String, Pair<String, Integer>> powerUps = new HashMap<>();

    @Override
    public Map<String, Pair<String, Integer>> getAll() {
        return this.powerUps;
    }

    @Override
    public Pair<String, Integer> getValue(String name) {
        return this.powerUps.get(name);
    }

    @Override
    public void setValue(String name, String type, int level) {
        this.powerUps.replace(name, new Pair<>(type, level));
    }

    @Override
    public void increment(String name) {
        String type = this.powerUps.get(name).getX();
        int level = this.powerUps.get(name).getY();
        this.powerUps.replace(name, new Pair<>(type, level + 1));
    }   
}
