package it.unibo.jetpackjoyride.graphics.impl;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

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

    //private final GamePanel game;
    private final MenuPanel menuPanel;
    private InputQueue inputHandler;
    //private final EndGamePanel endGame;
    private final ShopPanel shop;
    //private final StatisticsPanel statistics;

    public ViewImpl(final WorldGameStateImpl worldGameState,final InputQueue inputHandler) throws ParseException {
        this.setTitle("Jetpack Joyride");
        this.inputHandler = inputHandler;
       // this.game = new GamePanel(this.inputHandler);
        this.menuPanel = new MenuPanel(this.inputHandler);

        this.shop = new ShopPanel(inputHandler);
        //this.statistics = new StatisticsPanel(worldGameState.getStatistics());
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(game.getPreferredSize());
        this.setLocationRelativeTo(null);
        //this.setMinimumSize(this.game.getPreferredSize());
        this.pack();
        //this.getContentPane().add(this.menuPanel);

        //this.add(this.game);
        this.add(this.shop);
        this.shop.setVisible(false);
        this.add(this.menuPanel);
        this.menuPanel.setVisible(true);


        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    @Override
    public void renderGame() {
       // this.game.repaint();
    }

    @Override
    public void renderMenu() {
        this.menuPanel.repaint();
    }

    @Override
    public void renderShop() {
       this.shop.repaint();
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
      //  return this.game;
      return null;
    }
    
}
