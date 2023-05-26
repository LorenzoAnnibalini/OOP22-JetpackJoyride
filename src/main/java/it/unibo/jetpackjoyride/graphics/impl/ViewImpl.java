package it.unibo.jetpackjoyride.graphics.impl;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import it.unibo.jetpackjoyride.graphics.api.View;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;

/**
 * Implements the View interface of the game.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */
public class ViewImpl extends JFrame implements View {

    static final int SIZE = 48;
    static final float FLOAT_SIZE = 48f;
    private final GamePanel gamePanel;
    private final MenuPanel menuPanel;
    private final InputQueue inputHandler;
    private final InputPanel inputPanel;
    private final ShopPanel shopPanel;
    private final CardLayout card;
    private final JPanel cardPanel;
    private final EndGamePanel endGamePanel;
    private final StatisticsPanel statisticsPanel;
    private Font customFont;
    private static final String SEPARATOR = File.separator;
    private String fontPath = "src" + SEPARATOR
            + "main" + SEPARATOR
            + "resources" + SEPARATOR
            + "New Athletic M54.ttf";

    /**
     * Constructor of the ViewImpl.
     * 
     * @param worldGameState
     * @param inputHandler
     * @throws ParseException
     * @throws IOException
     */
    public ViewImpl(final WorldGameStateImpl worldGameState, final InputQueue inputHandler)
            throws ParseException, IOException {
        // load font
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(FLOAT_SIZE);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, SIZE);
        }
        this.setTitle("Jetpack Joyride");
        this.inputHandler = inputHandler;
        this.menuPanel = new MenuPanel(this.inputHandler, this.customFont);
        this.gamePanel = new GamePanel(this.customFont);
        this.inputPanel = new InputPanel(inputHandler);
        this.shopPanel = new ShopPanel(inputHandler, worldGameState.getGeneralStatistics(), this.customFont);
        this.endGamePanel = new EndGamePanel(inputHandler, worldGameState, this.customFont);
        this.statisticsPanel = new StatisticsPanel(inputHandler, this.customFont);
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
        this.endGamePanel.update();
        this.card.show(this.cardPanel, "endGamePanel");
    }

    @Override
    public void renderStatistics() throws FileNotFoundException, IOException {
        this.statisticsPanel.update();
        this.card.show(this.cardPanel, "statisticsPanel");
    }

    @Override
    public void close() {
        this.dispose();
        System.exit(0);
    }

    @Override
    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

}
