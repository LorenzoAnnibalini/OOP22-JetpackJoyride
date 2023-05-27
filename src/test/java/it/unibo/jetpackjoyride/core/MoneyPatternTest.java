package it.unibo.jetpackjoyride.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Queue;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.core.api.MoneyPatternLoader;
import it.unibo.jetpackjoyride.core.impl.MoneyPatternLoaderImpl;
import it.unibo.jetpackjoyride.model.impl.Money;

/**
 * Class to test the MoneyPatternLoader and the class Money.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
class MoneyPatternTest {

    private static final int FILE_NUMBER = 1;
    private final MoneyPatternLoader moneyPatternLoader = new MoneyPatternLoaderImpl(FILE_NUMBER);

    @Test
    void testMoneyPatternLoader() throws IOException {
        final int index = 0;
        final int moneyListSize = 28;
        final List<Money> moneyList = moneyPatternLoader.getMoneyPattern();
        // CHECKSTYLE: MagicNumber OFF
        /* rule deactivated because these are all values ​​of the x coordinates at different instants, 
         *it would be redundant to create a variable for each possible value
         */
        final Queue<Double> expectedXValue = new LinkedList<>(List.of(2330d, 2285d, 2248d, 2566d)); 
        // CHECKSTYLE: MagicNumber ON
        assertEquals(moneyListSize, moneyList.size());
        assertEquals(expectedXValue.poll(), moneyList.remove(index).getCurrentPos().getX());
        assertEquals(expectedXValue.poll(), moneyList.remove(index).getCurrentPos().getX());
        assertEquals(expectedXValue.poll(), moneyList.remove(index).getCurrentPos().getX());

        /*Rimuovo tutti gli elementi tranne l'ultimo*/
        while (moneyList.size() > 1) {
            moneyList.remove(index);
        }

        /*Controllo che anche l'ultimo sia giusto*/
        assertEquals(expectedXValue.poll(), moneyList.remove(index).getCurrentPos().getX());
    }
}
