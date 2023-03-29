package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to count total meters performed.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class TotalMeters extends StatisticsImpl {

    public TotalMeters(int value) {
        super(value);
    }

    @Override
    public void setValue(int amount) {
        super.value += amount;
    }

    @Override
    public void increment() {
    }

}
