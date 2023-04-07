package it.unibo.jetpackjoyride.model.impl;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Menu of the game
 * @author lorenzo.annibalini@studio.unibo.it
 */


public class MenuSwing {
    public MenuSwing() {
        JFrame mainJFrame = new JFrame();

        //General buttons
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

        //Main Page panels
        JPanel mainPage = new JPanel(new BorderLayout());
        JPanel mainPageOptions = new JPanel();
        JPanel mainPageComands = new JPanel();
        JButton exitMainPage = new JButton("Exit");
        JButton settings = new JButton("Settings");
        JButton newGame = new JButton("New Game");
        JButton shopButton = new JButton("Shop");
        JButton statistics = new JButton("Statistics");

        //Settings panels
        JPanel settingsPage = new JPanel(new BorderLayout());
        JPanel settingsPageOption = new JPanel();
        JPanel settingsPageComands = new JPanel();
        JButton exitSettingsPage = new JButton("Exit");
        JButton returnBack = new JButton("Return");
        JButton audio = new JButton("Audio ON");
        JButton setDifficulty = new JButton("Easy");


        //Default settings of JFrame  
        mainJFrame.setSize(600, 600);
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainJFrame.setTitle("Jetpack Joyride");
        mainJFrame.add(titlePanel, BorderLayout.NORTH);
        mainJFrame.add(mainPage);

        //Position of the panels in the mainPage
        mainPage.add(new JPanel(), BorderLayout.EAST);
        mainPage.add(new JPanel(), BorderLayout.WEST);
        mainPage.add(mainPageOptions, BorderLayout.CENTER);
        mainPage.add(mainPageComands, BorderLayout.SOUTH);

        //Position of the panels in the settingsPage
        settingsPage.add(new JPanel(), BorderLayout.EAST);
        settingsPage.add(new JPanel(), BorderLayout.WEST);
        settingsPage.add(settingsPageOption, BorderLayout.CENTER);
        settingsPage.add(settingsPageComands, BorderLayout.SOUTH);

/* ------------------------ MAIN PAGE -------------------------*/
        
        //gameOption panel
        mainPageOptions.setLayout(new GridLayout(3,1));
        mainPageOptions.add(newGame);
        mainPageOptions.add(statistics);
        mainPageOptions.add(shopButton);

        //settingsPageComand panel
        mainPageComands.add(settings);
        mainPageComands.add(exitMainPage);

        //set mainPage visible
        mainPage.setVisible(true);
        mainJFrame.setVisible(true);

/* ------------------------ SETTINGS PAGE -------------------------*/
        //settingsPageOption panel
        settingsPageOption.setLayout(new GridLayout(2,1));
        settingsPageOption.add(audio);
        settingsPageOption.add(setDifficulty);

        //settingsPageComand panel
        settingsPageComands.add(returnBack);
        settingsPageComands.add(exitSettingsPage);

        //set settingsPage invisible
        settingsPage.setVisible(false);


/* ------------------------ ACTION LISTENER -------------------------*/
        // if press exit button close the programm
        /*
         * exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
            }
        });
         */
        exitMainPage.addActionListener(e -> System.exit(0));
        exitSettingsPage.addActionListener(e -> System.exit(0));


        // if press settings button open the settings page
        settings.addActionListener(e -> {
            mainPage.setVisible(false);
            mainJFrame.add(settingsPage);
            title.setText(settingsPageText);
            settingsPage.setVisible(true);
            mainJFrame.setVisible(true);
        });

        // if press return button open the main page
        returnBack.addActionListener(e -> {
            settingsPage.setVisible(false);
            mainJFrame.add(mainPage);
            title.setText(welcomText);
            mainPage.setVisible(true);
            mainJFrame.setVisible(true);
        });

        // if press audio button set it to ON or OFF
        audio.addActionListener(e -> {
            if(audio.getText().equals("Audio ON")) {
                audio.setText("Audio OFF");
                //TODO: remove audio
            } else {
                audio.setText("Audio ON");
                //TODO: add audio
            }
        });

        // if press setDifficulty button set it to the next difficulty
        setDifficulty.addActionListener(e -> {
            if(setDifficulty.getText().equals("Easy")) {
                setDifficulty.setText("Medium");
                //TODO: set difficulty to medium
            }else if (setDifficulty.getText().equals("Medium")) {
                setDifficulty.setText("Hard");
                //TODO: set difficulty to hard
            }else if (setDifficulty.getText().equals("Hard")) {
                setDifficulty.setText("Easy");
                //TODO: set difficulty to easy
            }
        });
    }
    
}