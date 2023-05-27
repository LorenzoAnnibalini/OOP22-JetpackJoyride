package it.unibo.jetpackjoyride.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.core.impl.SavesImpl;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

/**
 * JUnit test for {@link SavesImpl}.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
class SavesTest {

    /**
     * Test for Download and Upload datas.
     * 
     * @throws IOException
     */
    private final Saves saves = new SavesImpl();
    private final Statistics stats = new StatisticsImpl();
    private static final int STATS_SIZE = 9;

    @Test
    void testDownloadAndUploadDatas() throws IOException {
        final Map<String, Integer> actualValues = saves.downloadSaves();
        // Test to count if there are 9 statistics downloaded
        assertEquals(STATS_SIZE, saves.downloadSaves().size());
        // CHECKSTYLE: MagicNumber OFF
        // Rule disabled because the numbers are used to test the correct functioning
        // and it would be redundant to create variables for each value
        final Map<String, Integer> statsMap = Map.of("MaxMoney", 0, "MaxMeters", 0, "MoneySpent", 0, "KilledNpc", 0,
                "Deaths",
                0,
                "GrabbedObjects", 0, "GrabbedMoney", 0, "TotalMeters", 0, "ActualMoney", 0);
        saves.uploadSaves(statsMap);
        stats.setAll(saves.downloadSaves());
        // Test to verify if the value are right
        assertEquals(statsMap, saves.downloadSaves());
        // Some operation on datas
        stats.setValue("KilledNpc", 5);
        stats.setValue("MaxMeters", 547);
        stats.increment("Deaths");
        stats.increment("MoneySpent", 2500);
        // Upload datas
        saves.uploadSaves(stats.getAll());
        // Test to verify if the value are still right after operations
        assertEquals(Map.of("MaxMoney", 0, "MaxMeters", 547, "MoneySpent", 2500, "KilledNpc", 5, "Deaths", 1,
                "GrabbedObjects", 0, "GrabbedMoney", 0, "TotalMeters", 0, "ActualMoney", 0), saves.downloadSaves());
        // Reset all datas
        // CHECKSTYLE: MagicNumber ON
        stats.setAll(actualValues);
        saves.uploadSaves(stats.getAll());
    }

}
