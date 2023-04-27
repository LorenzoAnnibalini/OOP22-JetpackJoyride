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

    private enum state {
        MENU,
        GAME,
        GAMEOVER
    }


    public GameEngineImpl() {
        this.inputHandler = new InputQueueImpl();
        this.view = new ViewImpl();
    }

    @Override
    public void worldGameStateStart() {
        this.worldGameState = new WorldGameStateImpl();
    }

 
    private void loopState(){
        long previousCycleStartTime = System.currentTimeMillis();
        while (true){
            long currentCycleStartTime = System.currentTimeMillis();
            long elapsedTime = currentCycleStartTime - previousCycleStartTime;
            this.processInput();
            this.updateWorldGameState();
            this.renderView();
            this.waitNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
    }

    private void processInput(){

    }
    
    private void updateWorldGameState(){

    }
    
    private void renderView(){
        view.render();
    }

    private void waitNextFrame(final long cycleStartTime){
        long dt = System.currentTimeMillis() - cycleStartTime;
		if (dt < this.framePeriod){
			try {
				Thread.sleep(this.framePeriod - dt);
			} catch (Exception ex){}
		}
    }

    @Override
    public void notifyInput(final Input input) {
        this.inputHandler.addInput(input);
    }


}
