package it.unibo.jetpackjoyride.graphics.impl;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.Font;

/**
 * Menu of the game
 * @author lorenzo.annibalini@studio.unibo.it
 */


public class MenuPanel {
    public MenuPanel() {
        JFrame mainJFrame = new JFrame();

        //General
        JTextArea title = new JTextArea();
        JPanel titlePanel = new JPanel();
        
        //Font of the title
        title.setEditable(false);
        title.setBackground(null);
        title.setFont( new Font("Arial", Font.BOLD, 30));
        titlePanel.add(title, Alignment.CENTER);
        final String welcomText = "Welcome to Jetpack Joyride !";
        final String settingsPageText = "Jetpack Joyride - Settings";
        title.setText(welcomText);

        //Component of the menu
        MenuMainPage mainPage = new MenuMainPage();
        MenuSettingsPage settingsPage = new MenuSettingsPage();

        //Default settings of JFrame  
        mainJFrame.setSize(600, 600);
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainJFrame.setTitle("Jetpack Joyride");
        mainJFrame.add(titlePanel, BorderLayout.NORTH);
        mainJFrame.add(mainPage.getMainPage());
        mainPage.getMainPage().setVisible(true);
        mainJFrame.setVisible(true);

/* ------------------------ ACTION LISTENER -------------------------*/

        // if press exit button close the programm
        mainPage.getExit().addActionListener(e -> System.exit(0));
        settingsPage.getExit().addActionListener(e -> System.exit(0));

        // if press settings button open the settings page
        mainPage.getSettings().addActionListener(e -> {
            mainPage.getMainPage().setVisible(false);
            mainJFrame.add(settingsPage.getSettingsPage());
            title.setText(settingsPageText);
            settingsPage.setVisible(true);
            mainJFrame.setVisible(true);
        });

        // if press return button open the main page
        settingsPage.getReturnBack().addActionListener(e -> {
            settingsPage.setVisible(false);
            mainJFrame.add(mainPage.getMainPage());
            title.setText(welcomText);
            mainPage.getMainPage().setVisible(true);
            mainJFrame.setVisible(true);
        });

        // if press NewGame button open the game
        mainPage.getNewGame().addActionListener(e -> {
           /* TODO: implement the game
            mainPage.getMainPage().setVisible(false);
            mainJFrame.add(new Game());
            mainJFrame.setVisible(true);
            */
        });

        // if press Statistics button open the statistics page
        mainPage.getStatistics().addActionListener(e -> {
           /* TODO: implement the statistics
            mainPage.getMainPage().setVisible(false);
            mainJFrame.add(new MenuStatistics());
            mainJFrame.setVisible(true);
            */
        });

        // if press Shop button open the shop page
        mainPage.getShopButton().addActionListener(e -> {
            /* TODO: implement the shop
            mainPage.getMainPage().setVisible(false);
            mainJFrame.add(new MenuShop());
            mainJFrame.setVisible(true);
            */
        });

    }
    
}