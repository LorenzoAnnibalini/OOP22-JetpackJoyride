package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;

import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.input.api.Input.TypeInput;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * This class is used to create the main page of the game.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class MenuMainPage extends JPanel {

    private static final long serialVersionUID = 12L;

    // Main Page panels
    private final JPanel mainPageOptions = new JPanel();
    private final JPanel mainPageComands = new JPanel();

    // Main Page buttons
    private final JButton exitButton = new JButton("Exit");
    private final JButton settingsButton = new JButton("Settings");
    private final JButton newGameButton = new JButton("New Game");
    private final JButton shopButton = new JButton("Shop");
    private final JButton statisticsButton = new JButton("Statistics");

    // Title of the main page
    private final JTextArea title = new JTextArea();
    private final JPanel titlePanel = new JPanel();
    static final float SIZE = 50f;

    /**
     * Constructor of the main page.
     * 
     * @param font
     */
    public MenuMainPage(final Font font, final InputQueue inputHandler) {

        // Main Page layout
        this.setLayout(new BorderLayout());

        // Font of the title
        title.setEditable(false);
        title.setBackground(null);
        title.setFont(font.deriveFont(SIZE));
        titlePanel.add(title, Alignment.CENTER);
        final String welcomText = "Welcome to Jetpack Joyride !";
        title.setText(welcomText);

        // Font of the buttons
        newGameButton.setFont(font);
        statisticsButton.setFont(font);
        shopButton.setFont(font);
        exitButton.setFont(font);
        settingsButton.setFont(font);

        // Position of the panels in the mainPage
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.WEST);
        this.add(mainPageOptions, BorderLayout.CENTER);
        this.add(mainPageComands, BorderLayout.SOUTH);

        // gameOption panel
        mainPageOptions.setLayout(new GridLayout(3, 1));
        mainPageOptions.add(newGameButton);
        mainPageOptions.add(statisticsButton);
        mainPageOptions.add(shopButton);

        // settingsPageComand panel
        // mainPageComands.add(settings);
        mainPageComands.add(exitButton);

        // set visible to false
        this.setVisible(false);

     /* ------------------------ ACTION LISTENER ------------------------- */

        // if press exit button close the programm
        this.exitButton.addActionListener(e -> inputHandler.addInput(new InputImpl(TypeInput.EXIT, "Exit")));
        // settingsPage.getExit().addActionListener(e -> inputHandler.addInput(new
        // InputImpl(typeInput.EXIT, "Exit")));

        // if press NewGame button open the game
        this.newGameButton.addActionListener(e -> inputHandler.addInput(new InputImpl(TypeInput.START_GAME, "New Game")));

        // if press Statistics button open the statistics page
        this.statisticsButton.addActionListener(e -> inputHandler.addInput(new InputImpl(TypeInput.STATISTICS, "Statistics")));

        // if press Shop button open the shop page
        this.shopButton.addActionListener(e -> inputHandler.addInput(new InputImpl(TypeInput.SHOP, "Shop")));

    }
}
