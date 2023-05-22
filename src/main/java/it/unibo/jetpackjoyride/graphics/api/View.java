package it.unibo.jetpackjoyride.graphics.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import it.unibo.jetpackjoyride.graphics.impl.GamePanel;

public interface View {
    /**
     * Method to render the start of the game.
     * @throws ParseException
     */
    void renderGame() throws ParseException;

    /**
     * Method to render the start menu.
     */
    void renderMenu();

    /**
     * Method to render the shop.
     */
    void renderShop();

    /**
     * Method to render end of the game.
     */
    void renderEndGame();

    /**
     * Method to render statistics.
     * @throws IOException
     * @throws FileNotFoundException
     */
    void renderStatistics() throws FileNotFoundException, IOException;

    /**
     * Method to get the game panel.
     */
    GamePanel getGamePanel();

}
