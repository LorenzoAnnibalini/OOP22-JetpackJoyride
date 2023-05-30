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
        final Map<String, Integer> statsMap = Map.of("Max Money", 0, "Max Meters", 0, "Money Spent", 0, "Killed NPC", 0,
                "Deaths",
                0,
                "Grabbed Objects", 0, "Grabbed Money", 0, "Total Meters", 0, "Actual Money", 0);
        saves.uploadSaves(statsMap);
        stats.setAll(saves.downloadSaves());
        // Test to verify if the value are right
        assertEquals(statsMap, saves.downloadSaves());
        // Some operation on datas
        stats.setValue("Killed NPC", 5);
        stats.setValue("Max Meters", 547);
        stats.increment("Deaths");
        stats.increment("Money Spent", 2500);
        // Upload datas
        saves.uploadSaves(stats.getAll());
        // Test to verify if the value are still right after operations
        assertEquals(Map.of("Max Money", 0, "Max Meters", 547, "Money Spent", 2500, "Killed NPC", 5, "Deaths", 1,
                "Grabbed Objects", 0, "Grabbed Money", 0, "Total Meters", 0, "Actual Money", 0), saves.downloadSaves());
        // Reset all datas
        // CHECKSTYLE: MagicNumber ON
        stats.setAll(actualValues);
        saves.uploadSaves(stats.getAll());
    }

}
