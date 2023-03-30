package it.unibo.jetpackjoyride.model.api;

/**
 * Interface of class to get, set and increment statistics.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public interface Statistics {

    /**
     * Getter method to take value of the statistic.
     * @return value of statistic
     */
    int getValue();

    /**
     * Setter method to set name of the statistic.
     * @param amount value to set/add
     */
    void setValue(final int amount);

    /**
     * Method to increment value of statistic.
     */
    void increment();    

    /**
     * Getter method to take statistic's name.
     * @return the name of the statistic
     */
    String getName();
}
