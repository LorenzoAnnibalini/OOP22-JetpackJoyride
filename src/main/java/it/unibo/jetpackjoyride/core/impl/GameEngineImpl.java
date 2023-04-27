package it.unibo.jetpackjoyride.core.impl;

import it.unibo.jetpackjoyride.core.api.GameEngine;
import it.unibo.jetpackjoyride.input.api.Input;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.input.impl.InputQueueImpl;
import it.unibo.jetpackjoyride.model.api.WorldGameState;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;

public class GameEngineImpl implements GameEngine {

    private InputQueue inputHandler;
    private View view;
    private long framePeriod = 20;
    private WorldGameState worldGameState;

    public GameEngineImpl() {
        this.inputHandler = new InputQueueImpl();
        this.view = new ViewImpl();
    }

    @Override
    public void worldGameStateStart() {
        this.worldGameState = new WorldGameStateImpl();
    }
 
    

    
    
}
