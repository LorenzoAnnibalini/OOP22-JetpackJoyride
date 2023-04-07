package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * This class is used to create the main page of the game.
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class MenuMainPage {
    
    //Main Page panels
    private JPanel mainPage = new JPanel(new BorderLayout());
    private JPanel mainPageOptions = new JPanel();
    private JPanel mainPageComands = new JPanel();

    //Main Page buttons
    private JButton exit = new JButton("Exit");
    private JButton settings = new JButton("Settings");
    private JButton newGame = new JButton("New Game");
    private JButton shopButton = new JButton("Shop");
    private JButton statistics = new JButton("Statistics");

    public MenuMainPage() {
        //Position of the panels in the mainPage
        mainPage.add(new JPanel(), BorderLayout.EAST);
        mainPage.add(new JPanel(), BorderLayout.WEST);
        mainPage.add(mainPageOptions, BorderLayout.CENTER);
        mainPage.add(mainPageComands, BorderLayout.SOUTH);

        //gameOption panel
        mainPageOptions.setLayout(new GridLayout(3,1));
        mainPageOptions.add(newGame);
        mainPageOptions.add(statistics);
        mainPageOptions.add(shopButton);

        //settingsPageComand panel
        mainPageComands.add(settings);
        mainPageComands.add(exit);

        //set visible to false
        mainPage.setVisible(false);
    }


 /* ------------------------ MAIN PAGE GETTER -------------------------*/

    /**
     * @return the mainPage
     */
    public JPanel getMainPage() {
        return mainPage;
    }

    /**
     * @return the mainPageOptions
     */
    public JButton getExit() {
        return exit;
    }

    /**
     * @return the mainPageComands
     */
    public JButton getSettings() {
        return settings;
    }

    /**
     * @return the exit
     */
    public JButton getNewGame() {
        return newGame;
    }

    /**
     * @return the settings
     */
    public JButton getShopButton() {
        return shopButton;
    }

    /**
     * @return the newGame
     */
    public JButton getStatistics() {
        return statistics;
    }
    
}
