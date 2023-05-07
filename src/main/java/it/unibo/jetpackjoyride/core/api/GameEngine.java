package it.unibo.jetpackjoyride.core.api;

import it.unibo.jetpackjoyride.input.api.Input;

/**
 * Interface for the game engine.
 * It is the core of the game, it manages the game loop and the game state.
 * also it manages the input, the rendering and the frame rate.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public interface GameEngine {
    public enum GameState {
        MAIN_MENU,
        GAME,
        GAMEOVER
    }

    /**
     * Start the game engine.
     */
    public void worldGameStateStart();

    /**
     * Notify an intup event to the game engine, it will be processed in the next
     * game loop.
     */
    public void notifyInput(final Input input);

    /**
     * Start the game loop of the game engine.
     * It will process inputs, update the game state, render the view and wait for
     * the next frame.
     */
    public void loopState();
}
