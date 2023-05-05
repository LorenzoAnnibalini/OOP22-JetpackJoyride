package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Menu of the game
 * @author lorenzo.annibalini@studio.unibo.it
 */


public class MenuPanel extends JPanel{

    public MenuPanel() {

        //Component of the menu
        MenuMainPage mainPage = new MenuMainPage();
        MenuSettingsPage settingsPage = new MenuSettingsPage();

        //Default settings
        this.setLayout(new BorderLayout());
        this.add(mainPage);
        mainPage.setVisible(true);
        this.setVisible(false);

/* ------------------------ ACTION LISTENER -------------------------*/

        // if press exit button close the programm
        mainPage.getExit().addActionListener(e -> System.exit(0));
        settingsPage.getExit().addActionListener(e -> System.exit(0));

        // if press settings button open the settings page
        mainPage.getSettings().addActionListener(e -> {
            mainPage.setVisible(false);
            this.add(settingsPage);
            settingsPage.setVisible(true);
            this.setVisible(true);
        });

        // if press return button open the main page
        settingsPage.getReturnBack().addActionListener(e -> {
            settingsPage.setVisible(false);
            this.add(mainPage);
            mainPage.setVisible(true);
            this.setVisible(true);
        });

        // if press NewGame button open the game
        mainPage.getNewGame().addActionListener(e -> {
           /* TODO: implement the game
            mainPage.setVisible(false);
            this.add(new Game());
            this.setVisible(true);
            */
        });

        // if press Statistics button open the statistics page
        mainPage.getStatistics().addActionListener(e -> {
           /* TODO: implement the statistics
            mainPage.setVisible(false);
            this.add(new MenuStatistics(), BorderLayout.CENTER);
            this.setVisible(true);
            */
        });

        // if press Shop button open the shop page
        mainPage.getShopButton().addActionListener(e -> {
            /* TODO: implement the shop
            mModainPage.setVisible(false);
            this.add(new MenuShop(), BorderLayout.CENTER);
            this.setVisible(true);
            */
        });  
    }
    
}