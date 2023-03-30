package it.unibo.jetpackjoyride.model.api;

import java.util.Map;
import java.util.Optional;

/**
 * Interface for game statistics.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public interface Statistics {

    /**
     * Method to get map of statistcs.
     */
    Map<String, Integer> getAll();

    /**
     * Getter of a statistic.
     * 
     * @param name tha name of the statistic to get the value
     * @return value of the statistic requested
     */
    int getValue(String name);

    /**
     * Setter for a statistic.
     * 
     * @param name  the name of the statistic that want to be set
     * @param value the new value of the statistic
     */
    void setValue(String name, int value);

    /**
     * Method to increment a statistic.
     * 
     * @param name  the name of the statistic tant want to be increment
     * @param value the optional value that we want to increment the statistic. If
     *              empty, default value is 1
     */
    void increment(String name, Optional<Integer> value);
}
