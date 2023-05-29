package it.unibo.jetpackjoyride.model;

import static org.junit.Assert.fail;

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

    /**
     * This is a test to check if the world game state is created and if the update
     * of the state is done.
     * 
     * @throws IOException
     */
    @Test
    public void worldGameTest() throws IOException {
        final InputQueue inputQueue = new InputQueueImpl();
        final long dt = 20;
        final WorldGameState world = new WorldGameStateImpl(inputQueue);
        world.newGame();
        world.updateState(dt);
        if (world.getMoney() != null && world.getGeneralStatistics().getAll() != null
                && world.getPlayer() != null && world.getWorldEntities() != null) {
            fail("error in WorldGameState update.");
        }
    }

}
