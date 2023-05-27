package it.unibo.jetpackjoyride;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import it.unibo.jetpackjoyride.core.api.GameEngine;
import it.unibo.jetpackjoyride.core.impl.GameEngineImpl;
import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.graphics.impl.ViewImpl;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputQueueImpl;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;

/**
 * Classe principale del gioco. All'avvio dell'applicazione verrà chiamato
 * questo
 * metodo per inizializzare e avviare il gioco.
 * 
 * @author mattia.burreli@studio.unibo.it
 */

public final class JetpackJoyride {

    private JetpackJoyride() {

    }

    /**
     * Main principale del gioco. All'avvio dell'applicazione verrà chiamato questo
     * metodo per inizializzare e avviare il gioco.
     * 
     * @param args
     * @throws ParseException
     * @throws IOException
     */
    public static void main(final String[] args) throws ParseException, IOException {

        final InputQueue inputHandler = new InputQueueImpl();
        final WorldGameStateImpl worldGameStateImpl = new WorldGameStateImpl(inputHandler);
        final View view = new ViewImpl(worldGameStateImpl, inputHandler);
        final GameEngine gameEngine = new GameEngineImpl(view, worldGameStateImpl, inputHandler);
        gameEngine.loopState();

    }

}
