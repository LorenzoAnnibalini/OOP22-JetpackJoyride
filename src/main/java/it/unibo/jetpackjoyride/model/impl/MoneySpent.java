package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to count the amount of money spent.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class MoneySpent extends StatisticsImpl {

    public MoneySpent(int value, String name) {
        super(value, name);
    }

    @Override
    public void setValue(int amount) {
        super.value += amount;
    }

    @Override
    public void increment() {
    }

}
