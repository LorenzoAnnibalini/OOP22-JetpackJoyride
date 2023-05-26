package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.JPanel;

import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.api.Input.TypeInput;
import it.unibo.jetpackjoyride.input.impl.InputImpl;

import java.awt.BorderLayout;
import java.awt.Font;

/**
 * Menu of the game
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class MenuPanel extends JPanel {


    public MenuPanel(final InputQueue inputHandler, Font font) {
        // Component of the menu
        final MenuMainPage mainPage = new MenuMainPage(font);
        // MenuSettingsPage settingsPage = new MenuSettingsPage();

        // Default settings
        this.setLayout(new BorderLayout());
        this.add(mainPage);
        mainPage.setVisible(true);
        this.setVisible(true);

        /* ------------------------ ACTION LISTENER ------------------------- */

        // if press exit button close the programm
        mainPage.getExit().addActionListener(e -> inputHandler.addInput(new InputImpl(TypeInput.EXIT, "Exit")));
        // settingsPage.getExit().addActionListener(e -> inputHandler.addInput(new
        // InputImpl(typeInput.EXIT, "Exit")));

        // if press NewGame button open the game
        mainPage.getNewGame()
                .addActionListener(e -> inputHandler.addInput(new InputImpl(TypeInput.START_GAME, "New Game")));

        // if press Statistics button open the statistics page
        mainPage.getStatistics()
                .addActionListener(e -> inputHandler.addInput(new InputImpl(TypeInput.STATISTICS, "Statistics")));

        // if press Shop button open the shop page
        mainPage.getShopButton().addActionListener(e -> inputHandler.addInput(new InputImpl(TypeInput.SHOP, "Shop")));
    }

}