package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.model.api.Statistics;

/**
 * This is the abstract class for the statistics.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 * 
 */
public abstract class StatisticsImpl implements Statistics {
    protected int value;
    private String name;

    public StatisticsImpl(final int value) {
        this.value = value;
    }

    /**
     * Method to get the value of the statistic.
     * 
     * @return the value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Method to set a new value for the statistic
     * 
     * @param amount new value to set
     */
    public abstract void setValue(final int amount);

    /**
     * Abstract method to increment
     */
    public abstract void increment();

    /**
     * Method to get statistic's name.
     * 
     * @return name of statistic
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method toString of the abstract class.
     * 
     * @return toString
     */
    @Override
    public String toString() {
        return "Name: " + this.name + "\t Value: " + this.value;
    }

}
