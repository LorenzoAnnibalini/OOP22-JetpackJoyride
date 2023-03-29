package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to count the amount of money spent
 */
public class MoneySpent extends StatisticsImpl {

    @Override
    public void setValue(int amount) {
        super.value+=amount;
    }

    @Override
    public void increment() {
    }
    
}
