package it.unibo.jetpackjoyride.graphics.impl;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.plaf.DimensionUIResource;

import org.json.simple.parser.ParseException;

import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;


/*
 * Implements the View interface of the game.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class ViewImpl extends JFrame implements View {

    //TODO: Add Shop, Game and Statistics panels

    private final GamePanel game;
    private final MenuPanel menuPanel;
    //private final EndGamePanel endGame;
    //private final ShopPanel shop;
    //private final StatisticsPanel statistics;

    public ViewImpl(final WorldGameStateImpl worldGameState) throws ParseException {
        this.setTitle("Jetpack Joyride");
        this.game = new GamePanel(worldGameState.getWorldEntities(), worldGameState.getPlayer(), worldGameState.getMoney());
        this.menuPanel = new MenuPanel();
        //this.shop = new ShopPanel();
        //this.statistics = new StatisticsPanel(worldGameState.getWorldStatistics());
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(game.getPreferredSize());
        this.setSize(game.getPreferredSize());
        this.setMinimumSize(this.menuPanel.getPreferredSize());
        this.pack();
        //this.getContentPane().add(this.menuPanel);

        this.add(game);
        this.add(menuPanel);


        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    @Override
    public void renderGame() {
        this.game.repaint();
    }

    @Override
    public void renderMenu() {
        this.menuPanel.repaint();
    }

    @Override
    public void renderShop() {
       // this.shop.repaint();
    }

    @Override
    public void renderEndGame() {
        //TODO: cosa devo fare qui? Chiamo statistics ?
    }

    @Override
    public void renderStatistics() {
        //this.statistics.repaint();
    }
    
}
