package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to count total amount of grabbed objects.
 */
public class GrabbedObjects extends StatisticsImpl {

    @Override
    public void setValue(int amount) {
        super.value+=amount;
    }

    @Override
    public void increment() {
    }
    
}
