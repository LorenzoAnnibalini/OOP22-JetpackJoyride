package it.unibo.jetpackjoyride.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.core.api.MoneyPatternLoader;
import it.unibo.jetpackjoyride.core.impl.MoneyPatternLoaderImpl;
import it.unibo.jetpackjoyride.model.impl.Money;

/**
 * Class to test the MoneyPatternLoader and the class Money.
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class MoneyPatternTest {

    private final int fileNumber = 1;
    private ArrayList<Money> moneyList = new ArrayList<>();
    private MoneyPatternLoader moneyPatternLoader = new MoneyPatternLoaderImpl(this.fileNumber);

    @Test
    void testMoneyPatternLoader() throws Exception {
        int index = 0;
        moneyList = moneyPatternLoader.getMoneyPattern();
        assertEquals(26, moneyList.size());
        assertEquals(227, moneyList.remove(index).getCurrentPos().x);
        assertEquals(209, moneyList.remove(index).getCurrentPos().x);
        assertEquals(195, moneyList.remove(index).getCurrentPos().x);
        assertEquals(189, moneyList.remove(index).getCurrentPos().x);
        assertEquals(192, moneyList.remove(index).getCurrentPos().x);
        assertEquals(206, moneyList.remove(index).getCurrentPos().x);
        assertEquals(227, moneyList.remove(index).getCurrentPos().x);

        assertEquals(262, moneyList.remove(index).getCurrentPos().y);
        assertEquals(280, moneyList.remove(index).getCurrentPos().y);
        assertEquals(299, moneyList.remove(index).getCurrentPos().y);
        assertEquals(314, moneyList.remove(index).getCurrentPos().y);
        assertEquals(314, moneyList.remove(index).getCurrentPos().y);
        assertEquals(296, moneyList.remove(index).getCurrentPos().y);
        assertEquals(280, moneyList.remove(index).getCurrentPos().y);
    }
}
