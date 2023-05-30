package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.JPanel;

import it.unibo.jetpackjoyride.input.api.InputQueue;

import java.awt.BorderLayout;
import java.awt.Font;

/**
 * Menu of the game.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class MenuPanel extends JPanel {

    private static final long serialVersionUID = 11L;

    /**
     * Constructor of the MenuPanel.
     * 
     * @param inputHandler
     * @param font
     * 
     */
    public MenuPanel(final InputQueue inputHandler, final Font font) {
        // Component of the menu
        final MenuMainPage mainPage = new MenuMainPage(font, inputHandler);
        // MenuSettingsPage settingsPage = new MenuSettingsPage();

        // Default settings
        this.setLayout(new BorderLayout());
        this.add(mainPage);
        mainPage.setVisible(true);
        this.setVisible(true);
    }

}
