package it.unibo.jetpackjoyride.model.api;

import java.io.IOException;
import java.util.List;

import it.unibo.jetpackjoyride.model.impl.Money;

/**
 * Interface to load money pattern from file.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public interface MoneyPatternLoader {

    /**
     * Method to get the money pattern from file.
     * 
     * @return an array list of Money
     */
    List<Money> getMoneyPattern() throws IOException;
}
