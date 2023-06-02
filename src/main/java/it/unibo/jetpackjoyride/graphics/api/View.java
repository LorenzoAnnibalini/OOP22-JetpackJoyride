package it.unibo.jetpackjoyride.graphics.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import it.unibo.jetpackjoyride.graphics.impl.GamePanel;

/**
 * Interface for the view.
 * It contains all the methods to render the game.
 * 
 */

public interface View {

    /**
     * Method to render the start of the game.
     * 
     * @throws ParseException
     */
    void renderGame() throws ParseException;

    /**
     * Method to render the start menu.
     * 
     */
    void renderMenu();

    /**
     * Method to render the shop.
     * 
     */
    void renderShop();

    /**
     * Method to render end of the game.
     * 
     */
    void renderEndGame();

    /**
     * Method to render statistics.
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */
    void renderStatistics() throws FileNotFoundException, IOException;

    /**
     * Method to launch an error dialog with the given message.
     * 
     * @param message to be displayed
     */
    void launchError(String message);

    /**
     * Method to get the game panel.
     * 
     * @return the game panel
     */
    GamePanel getGamePanel();

    /**
     * Method to close the Frame.
     * 
     */
    void close();

}
