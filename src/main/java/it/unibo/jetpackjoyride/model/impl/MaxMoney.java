package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to count amount of max money in one game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class MaxMoney extends StatisticsImpl {

    public MaxMoney(int value) {
        super(value);
    }

    @Override
    public void setValue(int amount) {
        super.value = amount;
    }

    @Override
    public void increment() {
    }

}
