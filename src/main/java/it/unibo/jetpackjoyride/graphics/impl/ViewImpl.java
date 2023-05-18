package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.JFrame;

import org.json.simple.parser.ParseException;

import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;


/*
 * Implements the View interface of the game.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class ViewImpl extends JFrame implements View {

    //TODO: Add Shop, Game and Statistics panels

    private GamePanel game;
    private final MenuPanel menuPanel;
    private InputQueue inputHandler;
    private final InputPanel inputPanel;
    //private final EndGamePanel endGame;
    private final ShopPanel shop;
    //private final StatisticsPanel statistics;

    public ViewImpl(final WorldGameStateImpl worldGameState,final InputQueue inputHandler) throws ParseException {
        this.setTitle("Jetpack Joyride");
        this.inputHandler = inputHandler;
        this.menuPanel = new MenuPanel(this.inputHandler);
        this.game = new GamePanel();
        this.inputPanel = new InputPanel(inputHandler);
        this.shop = new ShopPanel(inputHandler);
        //this.statistics = new StatisticsPanel(worldGameState.getStatistics());
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.game.getPreferredSize());
        this.setLocationRelativeTo(null);
        this.setMinimumSize(this.game.getPreferredSize());
        this.pack();
        //this.getContentPane().add(this.menuPanel);

        //this.add(this.game);
        this.add(this.shop);
        this.shop.setVisible(false);
        this.add(this.game);
        this.game.setVisible(false);
        this.add(this.inputPanel);
        this.add(this.menuPanel);
        this.menuPanel.setVisible(true);


        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    @Override
    public void renderGame() throws ParseException {
        this.game.repaint();
        this.menuPanel.setVisible(false);
        this.shop.setVisible(false);
        this.game.setVisible(true);
    }

    @Override
    public void renderMenu() {
        this.shop.setVisible(false);
        this.game.setVisible(false);
        this.menuPanel.setVisible(true);
    }

    @Override
    public void renderShop() {
        this.menuPanel.setVisible(false);
        this.game.setVisible(false);
        this.shop.setVisible(true);
    }

    @Override
    public void renderEndGame() {
        //TODO: cosa devo fare qui? Chiamo statistics ?
    }

    @Override
    public void renderStatistics() {
        //this.statistics.repaint();
    }

    public void close() {
        this.dispose();
    }

    @Override
    public GamePanel getGamePanel() {
      return this.game;
    }
    
}
