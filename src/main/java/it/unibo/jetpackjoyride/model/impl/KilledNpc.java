package it.unibo.jetpackjoyride.model.impl;

/**
 * Class to count Killed Npc.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 * 
 */
public class KilledNpc extends StatisticsImpl {

    public KilledNpc(int value, String name) {
        super(value, name);
    }

    @Override
    public void setValue(int amount) {
    }

    @Override
    public void increment() {
        super.value++;
    }

}
