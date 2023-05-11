package it.unibo.jetpackjoyride.core.impl;

import java.util.List;

import it.unibo.jetpackjoyride.core.api.GameEngine;
import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.input.api.Input;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;

public class GameEngineImpl implements GameEngine {

    private InputQueue inputHandler;
    private View view;
    private final long framePeriod = 20;
    private WorldGameStateImpl worldGameState;
    private GameState currentState;
    private Statistics statistics;

    public GameEngineImpl(final View view, final WorldGameStateImpl worldGameState, final InputQueue inputHandler) {
        this.inputHandler = inputHandler;
        this.currentState = GameState.MAIN_MENU;
        this.view = view;
        this.worldGameState = worldGameState;
        this.statistics = new StatisticsImpl();
    }

    @Override
    public void worldGameStateStart() {
        if (this.currentState == GameState.MAIN_MENU) {
            this.worldGameState.newGame();
            this.currentState = GameState.GAME;
        }
    }

    public void loopState() {
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
        for (final Input inputElem : inputQueue) {
            switch (inputElem.getType()) {

                case SHOP:
                    if (this.currentState == GameState.MAIN_MENU) {
                        this.currentState = GameState.SHOP_MENU;
                    }
                    break;

                case MENU:
                    this.currentState = GameState.MAIN_MENU;
                    break;

                case STATISTICS:
                    if (this.currentState == GameState.MAIN_MENU) {
                        this.currentState = GameState.STATISTICS_MENU;
                    }
                    break;

                case UP:
                    if (this.currentState == GameState.GAME) {
                        this.worldGameState.moveUp();
                    }
                    break;

                case EXIT:
                    if (this.currentState == GameState.MAIN_MENU) {
                        // this.view.close();
                    }
                    break;

                case END_GAME:
                    if (this.currentState == GameState.GAME) {
                        // this.statistics.addAll(this.worldGameState.getStatistics());
                        this.currentState = GameState.GAMEOVER;
                    }
                    break;

                case ENABLE:
                    break;

                case DISABLE:
                    break;

                case BUY:
                    break;

                case BUY_SKIN:
                    break;

                case SELECT_SKIN:
                    break;

                case START_GAME:
                    if (this.currentState == GameState.MAIN_MENU) {
                        this.worldGameState.newGame();
                    }
                    break;

                default:
                    throw new IllegalArgumentException("The type of input is NULL or is incorrect.");

            }
        }
        ;
    }

    private void updateWorldGameState(final long elapsedTime) {
        if (this.currentState == GameState.GAME) {
            this.worldGameState.updateState(elapsedTime);
        }
    }

    private void renderView() {
        switch (this.currentState) {
            case MAIN_MENU:
                this.view.renderMenu();
                break;
            case GAME:
                this.view.renderGame();
                break;
            case SHOP_MENU:
                this.view.renderShop();
                break;
            case STATISTICS_MENU:
                this.view.renderStatistics();
                break;
            case GAMEOVER:
                this.view.renderEndGame();
                break;
            default:
                throw new IllegalArgumentException("The type of input is NULL or is incorrect.");
        }

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
