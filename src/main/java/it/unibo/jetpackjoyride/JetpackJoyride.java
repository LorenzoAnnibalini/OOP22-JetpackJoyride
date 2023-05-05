package it.unibo.jetpackjoyride;

import it.unibo.jetpackjoyride.core.api.GameEngine;
import it.unibo.jetpackjoyride.core.impl.GameEngineImpl;
import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.graphics.impl.ViewImpl;
import it.unibo.jetpackjoyride.model.api.WorldGameState;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;

public class JetpackJoyride {
    public static void main(String[] args) {
        WorldGameStateImpl world = new WorldGameStateImpl();
        System.out.println("a");
        ViewImpl view = new ViewImpl(world);
        System.out.println("b");
        GameEngine gameEngine = new GameEngineImpl(view, world);
        System.out.println("c");
    }
}