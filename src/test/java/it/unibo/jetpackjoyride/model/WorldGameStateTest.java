package it.unibo.jetpackjoyride.model;

import java.io.IOException;

import org.junit.Test;

import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputQueueImpl;
import it.unibo.jetpackjoyride.model.api.WorldGameState;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;

/**
 * This is a class to test the world game state.
 * It test the creation of the world game state and the update of the state.
 * It test also if the entities, moneys and player are created and allocated.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public class WorldGameStateTest {

    InputQueue inputQueue = new InputQueueImpl();

    @Test
    public void WorldGameTest() throws IOException {
        long dt = 20;
        WorldGameState world = new WorldGameStateImpl(this.inputQueue);
        world.newGame();
        world.updateState(dt);
        assert (world.getMoney().isEmpty() || !world.getMoney().isEmpty());
        assert (world.getGeneralStatistics().getAll().isEmpty() || !world.getGeneralStatistics().getAll().isEmpty());
        assert (world.getPlayer().isStatusPlayer());
        assert (world.getWorldEntities().isEmpty() || !world.getWorldEntities().isEmpty());
    }

}
