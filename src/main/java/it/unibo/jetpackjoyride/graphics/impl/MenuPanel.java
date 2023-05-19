package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.JPanel;

import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.api.Input.typeInput;
import it.unibo.jetpackjoyride.input.impl.InputImpl;

import java.awt.BorderLayout;

/**
 * Menu of the game
 * @author lorenzo.annibalini@studio.unibo.it
 */


public class MenuPanel extends JPanel{

    public MenuPanel(final InputQueue inputHandler) {

        //Component of the menu
        MenuMainPage mainPage = new MenuMainPage();
        //MenuSettingsPage settingsPage = new MenuSettingsPage();

        //Default settings
        this.setLayout(new BorderLayout());
        this.add(mainPage);
        mainPage.setVisible(true);
        this.setVisible(true);

/* ------------------------ ACTION LISTENER -------------------------*/

        // if press exit button close the programm
        mainPage.getExit().addActionListener(e -> inputHandler.addInput(new InputImpl(typeInput.EXIT, "Exit")));
        //settingsPage.getExit().addActionListener(e -> inputHandler.addInput(new InputImpl(typeInput.EXIT, "Exit")));

      /*
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

        */

        // if press NewGame button open the game
        mainPage.getNewGame().addActionListener(e -> inputHandler.addInput(new InputImpl(typeInput.START_GAME, "New Game")));

        // if press Statistics button open the statistics page
        mainPage.getStatistics().addActionListener(e -> inputHandler.addInput(new InputImpl(typeInput.STATISTICS, "Statistics")));

        // if press Shop button open the shop page
        mainPage.getShopButton().addActionListener(e -> inputHandler.addInput(new InputImpl(typeInput.SHOP, "Shop")));  
    }
    
}