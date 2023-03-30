package it.unibo.jetpackjoyride.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.core.impl.*;

public class SavesTest {

    /**
     * @throws IOException
     */
    @Test
    void testDownloadDatas() throws IOException {
        Saves.downloadSaves();
        int value1 = Saves.getDeaths().getValue();
        Saves.getDeaths().increment();
        Saves.uploadSaves();
        Saves.downloadSaves();
        assertEquals(value1 + 1, Saves.getDeaths().getValue());
    }

    
}