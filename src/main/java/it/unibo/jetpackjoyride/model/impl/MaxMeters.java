package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to save a new record for max meters performed
 */
public class MaxMeters extends StatisticsImpl {

    @Override
    public void setValue(int amount) {
        super.value = amount;
    }

    @Override
    public void increment() {
    }

}
