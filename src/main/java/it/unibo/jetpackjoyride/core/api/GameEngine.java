package it.unibo.jetpackjoyride.core.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

/**
 * Interface for the game engine.
 * It is the core of the game, it manages the game loop and the game state.
 * also it manages the input, the rendering and the frame rate.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public interface GameEngine {

    /**
     * Enum for the game state of the game engine.
     */
    enum GameState {

        /**
         * The gameEngine is in the main menu.
         */
        MAIN_MENU,

        /**
         * The gameEngine is in the game.
         */
        GAME,

        /**
         * The gameEngine is in the game over menu.
         */
        GAMEOVER,

        /**
         * The gameEngine is in the shop menu.
         */
        SHOP_MENU,

        /**
         * The gameEngine is in the statistics menu.
         */
        STATISTICS_MENU,

        /**
         * The gameEngine is in the settings menu.
         */
        SETTINGS_MENU
    }

    /**
     * Start the game engine.
     */
    void worldGameStateStart();

    /**
     * Start the game loop of the game engine.
     * It will process inputs, update the game state, render the view and wait for
     * the next frame.
     * @throws ParseException
     * @throws IOException
     * @throws FileNotFoundException
     */
    void loopState() throws ParseException, FileNotFoundException, IOException;
}
