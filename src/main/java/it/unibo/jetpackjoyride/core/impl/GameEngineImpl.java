package it.unibo.jetpackjoyride.core.impl;

import java.util.List;

import it.unibo.jetpackjoyride.core.api.GameEngine;
import it.unibo.jetpackjoyride.input.api.Input;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputQueueImpl;
import it.unibo.jetpackjoyride.model.api.WorldGameState;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;

public class GameEngineImpl implements GameEngine {

    private InputQueue inputHandler;
    // private View view;
    private long framePeriod = 20;
    private WorldGameState worldGameState;
    private state currentState;

    private enum state {
        MAIN_MENU,
        GAME,
        GAMEOVER
    }

    public GameEngineImpl() {
        this.inputHandler = new InputQueueImpl();
        // this.view = new ViewImpl();
    }

    @Override
    public void worldGameStateStart() {
        if(this.currentState == state.MAIN_MENU) {
        this.worldGameState = new WorldGameStateImpl();
        }
    }

    private void loopState() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (true) {
            long currentCycleStartTime = System.currentTimeMillis();
            long elapsedTime = currentCycleStartTime - previousCycleStartTime;
            this.processInput();
            this.updateWorldGameState(elapsedTime);
            this.renderView();
            this.waitNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
    }

    private void processInput() {
        List<Input> inputQueue = this.inputHandler.getInputQueue();
        for (final Input inputElem: inputQueue){
            switch(inputElem.getType()){

                case SHOP: 
                break;
                
                case MENU: 
                break;
                
                case UP: 
                break;
                
                case EXIT:
                break;
                
                default: throw new IllegalArgumentException("The type of input is NULL or is incorrect.");

            }
        }
        
    }

    private void updateWorldGameState(final long elapsedTime) {
        
    }

    private void renderView() {
        // view.render();
    }

    private void waitNextFrame(final long cycleStartTime) {
        long dt = System.currentTimeMillis() - cycleStartTime;
        if (dt < this.framePeriod) {
            try {
                Thread.sleep(this.framePeriod - dt);
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void notifyInput(final Input input) {
        this.inputHandler.addInput(input);
    }

}
