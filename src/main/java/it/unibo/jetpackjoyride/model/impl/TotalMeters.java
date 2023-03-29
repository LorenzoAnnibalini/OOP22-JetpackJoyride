package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to count total meters performed
 */
public class TotalMeters extends StatisticsImpl {

    @Override
    public void setValue(int amount) {
        super.value += amount;
    }

    @Override
    public void increment() {
    }

}
