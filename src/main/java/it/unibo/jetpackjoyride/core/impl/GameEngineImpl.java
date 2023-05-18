package it.unibo.jetpackjoyride.core.impl;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import it.unibo.jetpackjoyride.core.api.GameEconomy;
import it.unibo.jetpackjoyride.core.api.GameEngine;
import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.input.api.Input;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.model.impl.SkinInfoImpl;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;

/**
 * This is a class to implemate the game engine interface.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public class GameEngineImpl implements GameEngine {

    private InputQueue inputHandler;
    private View view;
    private final long framePeriod = 20;
    private WorldGameStateImpl worldGameState;
    private GameState currentState;
    private SkinInfoLoaderImpl skinInfoLoader;
    private GadgetLoaderImpl gadgetLoader;
    private GameEconomy gameEconomy;

    /**
     * Constructor for the game engine. It needs a view, a worldGameState and an
     * inputHandler. It set the current state to MAIN_MENU
     * and download the skin and the gadget.
     * 
     * @param view
     * @param worldGameState
     * @param inputHandler
     */
    public GameEngineImpl(final View view, final WorldGameStateImpl worldGameState, final InputQueue inputHandler) {
        this.inputHandler = inputHandler;
        this.currentState = GameState.MAIN_MENU;
        this.view = view;
        this.worldGameState = worldGameState;
        this.skinInfoLoader = new SkinInfoLoaderImpl();
        this.gadgetLoader = new GadgetLoaderImpl();
        this.gameEconomy = new GameEconomyImpl();
    }

    @Override
    public void worldGameStateStart() {
        if (this.currentState == GameState.MAIN_MENU || this.currentState == GameState.GAMEOVER) {
            this.worldGameState.newGame();
            this.view.getGamePanel().setMoney(this.worldGameState.getMoney());
            this.view.getGamePanel().setEntities(this.worldGameState.getWorldEntities());
            this.view.getGamePanel().setPlayer(this.worldGameState.getPlayer());
            this.currentState = GameState.GAME;
        }
    }

    @Override
    public void loopState() throws ParseException {
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

    /**
     * process the input from the input handler. It will do different things based
     * on the input type.
     */
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

                case SETTINGS:
                    if (this.currentState == GameState.MAIN_MENU) {
                        this.currentState = GameState.SETTINGS_MENU;
                    }
                    break;

                case UP_PRESSED:
                    if (this.currentState == GameState.GAME) {
                        this.worldGameState.moveUp();
                    }
                    break;

                case UP_RELEASED:
                    if (this.currentState == GameState.GAME) {
                        this.worldGameState.moveStatic();
                    }
                    break;

                case EXIT:
                    if (this.currentState == GameState.MAIN_MENU) {
                        try {
                            this.gadgetLoader.uploadGadget(new GadgetImpl().getAll());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            this.skinInfoLoader.uploadSkin(new SkinInfoImpl().getAll());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // this.view.close();
                    }
                    break;

                case END_GAME:
                    if (this.currentState == GameState.GAME) {
                        this.currentState = GameState.GAMEOVER;
                    }
                    break;

                case ENABLE:
                    this.gameEconomy.enableGadget(inputElem.getName().get());
                    break;

                case DISABLE:
                    this.gameEconomy.disableGadget(inputElem.getName().get());
                    break;

                case BUY:
                    this.gameEconomy.buyGadget(inputElem.getName().get());
                    break;

                case BUY_SKIN:
                    this.gameEconomy.buySkin(inputElem.getName().get());
                    break;

                case SELECT_SKIN:
                    this.gameEconomy.selectSkin(inputElem.getName().get());
                    break;

                case START_GAME:
                    if (this.currentState == GameState.MAIN_MENU || this.currentState == GameState.GAMEOVER) {
                        this.worldGameStateStart();
                    }
                    break;

                default:
                    throw new IllegalArgumentException("The type of input is NULL or is incorrect.");

            }
        }
        ;
    }

    /**
     * update the world game state.
     * 
     * @param elapsedTime
     */
    private void updateWorldGameState(final long elapsedTime) {
        if (this.currentState == GameState.GAME) {
            this.worldGameState.updateState(elapsedTime);
        }
    }

    /**
     * render the view. It will render things based on the current state.
     * 
     * @throws ParseException
     */
    private void renderView() throws ParseException {
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
            case SETTINGS_MENU:
                // this.view.renderSettings();
                break;
            default:
                throw new IllegalArgumentException("The type of input is NULL or is incorrect.");
        }

    }

    /**
     * wait the next frame.
     * 
     * @param cycleStartTime
     */
    private void waitNextFrame(final long cycleStartTime) {
        long dt = System.currentTimeMillis() - cycleStartTime;
        if (dt < this.framePeriod) {
            try {
                Thread.sleep(this.framePeriod - dt);
            } catch (Exception ex) {
            }
        }
    }

}
