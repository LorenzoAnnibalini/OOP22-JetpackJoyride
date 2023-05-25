package it.unibo.jetpackjoyride.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.core.api.MoneyPatternLoader;
import it.unibo.jetpackjoyride.core.impl.MoneyPatternLoaderImpl;
import it.unibo.jetpackjoyride.model.impl.Money;

/**
 * Class to test the MoneyPatternLoader and the class Money.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class MoneyPatternTest {

    private final int fileNumber = 1;
    private ArrayList<Money> moneyList = new ArrayList<>();
    private final MoneyPatternLoader moneyPatternLoader = new MoneyPatternLoaderImpl(this.fileNumber);

    @Test
    void testMoneyPatternLoader() throws Exception {
        final int index = 0;
        moneyList = moneyPatternLoader.getMoneyPattern();
        assertEquals(28, moneyList.size());
        assertEquals(2330, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2285, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2248, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2233, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2248, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2285, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2330, moneyList.remove(index).getCurrentPos().getX());

        assertEquals(232, moneyList.remove(index).getCurrentPos().getY());
        assertEquals(276, moneyList.remove(index).getCurrentPos().getY());
        assertEquals(318, moneyList.remove(index).getCurrentPos().getY());
        assertEquals(369, moneyList.remove(index).getCurrentPos().getY());
        assertEquals(364, moneyList.remove(index).getCurrentPos().getY());
        assertEquals(323, moneyList.remove(index).getCurrentPos().getY());
        assertEquals(323, moneyList.remove(index).getCurrentPos().getY());
    }
}
