package it.unibo.jetpackjoyride.graphics.api;

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

}
