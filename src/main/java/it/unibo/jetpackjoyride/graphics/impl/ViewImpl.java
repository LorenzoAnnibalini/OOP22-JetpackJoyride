package it.unibo.jetpackjoyride.graphics.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.model.impl.GameObject;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;
import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.impl.Money;


/*
 * Implements the View interface of the game.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class ViewImpl implements View {

    //TODO: Add Shop, Game and Statistics panels
    
    private final JFrame frame;
    private final GamePanel game;
    private final MenuPanel menuPanel;
    //private final EndGamePanel endGame;
    //private final ShopPanel shop;
    private final StatisticsPanel statistics;

    public ViewImpl(final Map <String,Integer> statisticsMap ,final Set<Pair<String, GameObject>> entities, final PlayerImpl player, final List<Money> money) {
        this.frame = new JFrame("Jetpack Joyride");
        this.game = new GamePanel(entities, player, money);
        this.menuPanel = new MenuPanel();
        //this.shop = new ShopPanel();
        this.statistics = new StatisticsPanel(statisticsMap);
       
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(this.menuPanel.getPreferredSize());
        this.frame.setMinimumSize(this.menuPanel.getPreferredSize());
        this.frame.pack();
        this.frame.getContentPane().add(this.menuPanel);
        this.frame.setVisible(true);
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
        this.statistics.repaint();
    }
    
}
