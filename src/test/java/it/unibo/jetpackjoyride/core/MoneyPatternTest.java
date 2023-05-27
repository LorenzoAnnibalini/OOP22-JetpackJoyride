package it.unibo.jetpackjoyride.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.io.IOException;
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

    private final static int fileNumber = 1;
    private final MoneyPatternLoader moneyPatternLoader = new MoneyPatternLoaderImpl(fileNumber);

    @Test
    void testMoneyPatternLoader() throws IOException {
        final int index = 0;
        List<Money> moneyList = new ArrayList<>();
        moneyList = moneyPatternLoader.getMoneyPattern();
        assertEquals(28, moneyList.size());
        assertEquals(2330, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2285, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2248, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2233, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2248, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2285, moneyList.remove(index).getCurrentPos().getX());
        assertEquals(2330, moneyList.remove(index).getCurrentPos().getX());

        /*Rimuovo tutti gli elementi tranne l'ultimo*/
        while(moneyList.size() > 1) {
            moneyList.remove(index);
        }

        /*Controllo che anche l'ultimo sia giusto*/
        assertEquals(2566, moneyList.remove(index).getCurrentPos().getX());
    }
}
