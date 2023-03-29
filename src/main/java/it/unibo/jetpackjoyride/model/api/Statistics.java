package it.unibo.jetpackjoyride.model.api;

/**
 * Interface of class to get, set and increment statistics
 */
public interface Statistics {

    int getValue();

    void setValue(final int amount);

    void increment();    

    String getName();
}
