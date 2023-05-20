package it.unibo.jetpackjoyride.graphics.impl;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.model.api.Player;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;


/*
 * Implements the View interface of the game.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class ViewImpl extends JFrame implements View {

    //TODO: Add Shop, Game and Statistics panels

    private GamePanel gamePanel;
    private final MenuPanel menuPanel;
    private InputQueue inputHandler;
    private final InputPanel inputPanel;
    private final ShopPanel shopPanel;
    private final CardLayout card;
    private final JPanel cardPanel;
    private final EndGamePanel endGamePanel;
    private final StatisticsPanel statisticsPanel;

    public ViewImpl(final WorldGameStateImpl worldGameState,final InputQueue inputHandler) throws ParseException {
        this.setTitle("Jetpack Joyride");
        this.inputHandler = inputHandler;
        this.menuPanel = new MenuPanel(this.inputHandler);
        this.gamePanel = new GamePanel();
        this.inputPanel = new InputPanel(inputHandler);
        this.shopPanel = new ShopPanel(inputHandler);
        this.endGamePanel = new EndGamePanel(inputHandler, worldGameState.getPlayer().getStatistics());
        this.statisticsPanel = new StatisticsPanel(inputHandler, worldGameState.getGeneralStatistics());
        this.card = new CardLayout();
        this.cardPanel = new JPanel(this.card);

       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.gamePanel.getPreferredSize());
        this.setLocationRelativeTo(null);
        this.setMinimumSize(this.gamePanel.getPreferredSize());
        this.pack();

        this.add(this.inputPanel);

        this.cardPanel.add(gamePanel, "gamePanel");
        this.cardPanel.add(menuPanel, "menuPanel");
        this.cardPanel.add(shopPanel, "shopPanel");
        this.cardPanel.add(statisticsPanel, "statisticsPanel");
        this.cardPanel.add(endGamePanel, "endGamePanel");
        this.add(cardPanel);
        this.card.show(this.cardPanel, "menuPanel");
        this.setResizable(false);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    @Override
    public void renderGame() throws ParseException {
        this.gamePanel.repaint();
        this.card.show(this.cardPanel, "gamePanel");
    }

    @Override
    public void renderMenu() {
        this.card.show(this.cardPanel, "menuPanel");
    }

    @Override
    public void renderShop() {
        this.shopPanel.update();
        this.card.show(this.cardPanel, "shopPanel");
    }

    @Override
    public void renderEndGame() {
        this.card.show(this.cardPanel, "endGamePanel");
    }

    @Override
    public void renderStatistics() {
        this.statisticsPanel.update();
        this.card.show(this.cardPanel, "statisticsPanel");
    }

    public void close() {
        this.dispose();
        System.exit(0);
    }

    @Override
    public GamePanel getGamePanel() {
      return this.gamePanel;
    }
    
}
