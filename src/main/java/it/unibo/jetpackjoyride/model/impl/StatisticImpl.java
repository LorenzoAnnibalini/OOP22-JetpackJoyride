package it.unibo.jetpackjoyride.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.jetpackjoyride.model.api.Statistics;
/**
 * Class to modelize the statisstics of the game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class StatisticImpl implements Statistics{

    private Map<String, Integer> statistics = new HashMap<>();

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
    public void increment(String name, Optional<Integer> value) {
        if(value.isPresent()) {
            statistics.replace(name, statistics.get(name) + value.get());
        } else {
            statistics.replace(name, statistics.get(name) + 1);
        }
    }
    
}
