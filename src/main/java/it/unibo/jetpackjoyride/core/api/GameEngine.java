package it.unibo.jetpackjoyride.core.api;

import it.unibo.jetpackjoyride.input.api.Input;

/**
 * Interface for the game engine.
 * It is the core of the game, it manages the game loop and the game state.
 * also it manages the input, the rendering and the frame rate.
 * @author mattia.burreli@studio.unibo.it
 */
public interface GameEngine {

    /**
     * Notify an intup event to the game engine, it will be processed in the next game loop.
     */
    void notifyInput(Input input);
}
