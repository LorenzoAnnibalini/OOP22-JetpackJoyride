package it.unibo.jetpackjoyride;

import it.unibo.jetpackjoyride.core.api.GameEngine;
import it.unibo.jetpackjoyride.core.impl.GameEngineImpl;
import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.graphics.impl.ViewImpl;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;

public class JetpackJoyride {

    /**
     * Main principale del gioco. All'avvio dell'applicazione verrà chiamato questo
     * metodo per inizializzare e avviare il gioco.
     */
    public static void main() {

        WorldGameStateImpl worldGameStateImpl = new WorldGameStateImpl();
        View view = new ViewImpl(worldGameStateImpl);
        GameEngine gameEngine = new GameEngineImpl(view, worldGameStateImpl);
        gameEngine.loopState();

    }

}
