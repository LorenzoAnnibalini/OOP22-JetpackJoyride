package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to count number of Deaths
 * 
 * @author emanuele.sanchi@studio.unibo.it
 * 
 */
public class Deaths extends StatisticsImpl {

    @Override
    public void increment() {
        super.value++;
    }

    @Override
    public void setValue(int amount) {
    }

}
