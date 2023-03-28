package it.unibo.jetpackjoyride.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.core.impl.*;

public class SavesTest {
    Saves s = new SavesImpl();

    @Test
    void testDownloadDatas() throws FileNotFoundException {
        assertEquals(List.of(0,0,0,0,0,0,0,0,0), this.s.downloadSaves());
    }
    
}