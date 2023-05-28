package it.unibo.jetpackjoyride.model;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import it.unibo.jetpackjoyride.core.impl.GameSettingsImpl;

/**
 * JUnit class is used to test the settings of the game.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */
public class GameSettingsTest {

    /**
     * This method is used to test the settings of the game.
     * 
     * @throws FileNotFoundException
    */
    @Test
    public void defaultTest() throws FileNotFoundException {
        GameSettingsImpl settings = new GameSettingsImpl();
    }

    /**
     * This method is used to test if the class write the settings of the game.
     * 
     * @throws IOException
     */
    @Test
    public void writeTest() throws IOException {
        GameSettingsImpl settings = new GameSettingsImpl();
        settings.setValue("audio", "Audio ON");
        settings.setValue("difficulty", "Difficult : HARD");
        settings.writeSettings();
    }
}
