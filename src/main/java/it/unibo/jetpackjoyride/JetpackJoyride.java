package it.unibo.jetpackjoyride;

import it.unibo.jetpackjoyride.core.api.GameEngine;
import it.unibo.jetpackjoyride.core.impl.GameEngineImpl;
import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.graphics.impl.ViewImpl;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputQueueImpl;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;

/**
 * Classe principale del gioco. All'avvio dell'applicazione verrà chiamato questo
 * metodo per inizializzare e avviare il gioco.
 * @author mattia.burreli@studio.unibo.it
 */
public class JetpackJoyride {

    /**
     * Main principale del gioco. All'avvio dell'applicazione verrà chiamato questo
     * metodo per inizializzare e avviare il gioco.
     */
    public static void main() {

        InputQueue inputHandler=new InputQueueImpl();
        WorldGameStateImpl worldGameStateImpl = new WorldGameStateImpl(inputHandler);
        View view = new ViewImpl(worldGameStateImpl,inputHandler);
        GameEngine gameEngine = new GameEngineImpl(view, worldGameStateImpl,inputHandler);
        gameEngine.loopState();

    }

}
