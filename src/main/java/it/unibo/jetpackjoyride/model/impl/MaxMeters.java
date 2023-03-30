package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to save a new record for max meters performed.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class MaxMeters extends StatisticsImpl {

    public MaxMeters(int value, String name) {
        super(value, name);
    }
    @Override
    public void setValue(int amount) {
        super.value = amount;
    }

    @Override
    public void increment() {
    }

}
