package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to count total amount of grabbed objects.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class GrabbedObjects extends StatisticsImpl {

    public GrabbedObjects(int value) {
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
