package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to count amount of max money in one game
 */
public class MaxMoney extends StatisticsImpl{

    @Override
    public void setValue(int amount) {
        super.value = amount;
    }

    @Override
    public void increment() {
    }
    
}
