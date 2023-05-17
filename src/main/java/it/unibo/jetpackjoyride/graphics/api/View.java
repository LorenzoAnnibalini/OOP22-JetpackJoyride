package it.unibo.jetpackjoyride.graphics.api;

import javax.swing.JPanel;

import it.unibo.jetpackjoyride.graphics.impl.GamePanel;

public interface View {
    /**
     * Method to render the start of the game.
     */
    void renderGame();

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
     */
    void renderStatistics();

    /**
     * Method to get the game panel.
     */
    GamePanel getGamePanel();

}
