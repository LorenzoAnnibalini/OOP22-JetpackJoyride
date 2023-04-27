package it.unibo.jetpackjoyride.core.api;

/**
 * Interface for the game engine.
 * It is the core of the game, it manages the game loop and the game state.
 * also it manages the input, the rendering and the frame rate.
 * @author mattia.burreli@studio.unibo.it
 */
public interface GameEngine {

    /**
     * Start the game engine.
     */
    public void worldGameStateStart();
}
