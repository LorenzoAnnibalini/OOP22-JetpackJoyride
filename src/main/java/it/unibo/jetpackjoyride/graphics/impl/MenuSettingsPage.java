package it.unibo.jetpackjoyride.graphics.impl;

import it.unibo.jetpackjoyride.core.impl.GameSettingsImpl;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;

/**
 * This class is used to create the settings page of the game.
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class MenuSettingsPage extends JPanel {
    
    //Settings panels
    JPanel settingsPageOption = new JPanel();
    JPanel settingsPageComands = new JPanel();

    //Title of the settings page
    JTextArea title = new JTextArea();
    JPanel titlePanel = new JPanel();

    //Settings buttons
    JButton exit = new JButton("Exit");
    JButton returnBack = new JButton("Return");
    JButton audio = new JButton("Audio ON");
    JButton difficulty = new JButton("Easy");

    public MenuSettingsPage(){

        //Settings Page layout
        this.setLayout(new BorderLayout());

        //Font of the title
        title.setEditable(false);
        title.setBackground(null);
        title.setFont( new Font("Arial", Font.BOLD, 30));
        titlePanel.add(title, Alignment.CENTER);
        final String settingsPageText = "Jetpack Joyride - Settings";
        title.setText(settingsPageText);

        //Position of the panels in the settingsPage
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.WEST);
        this.add(settingsPageOption, BorderLayout.CENTER);
        this.add(settingsPageComands, BorderLayout.SOUTH);

        //settingsPageOption panel
        settingsPageOption.setLayout(new GridLayout(2,1));
        settingsPageOption.add(audio);
        settingsPageOption.add(difficulty);

        //settingsPageComand panel
        settingsPageComands.add(returnBack);
        settingsPageComands.add(exit);

        //Set visible to false
        this.setVisible(false);

        //Load the settings from the file
        loadSettings();

        //It will change the state of the button from ON to OFF and viceversa
        this.audio.addActionListener(e -> {
            if(audio.getText().equals("Audio ON")) {
                audio.setText("Audio OFF");
                //TODO: remove audio
            } else {
                audio.setText("Audio ON");
                //TODO: add audio
            }
            this.saveSettings();
        });

        //It will change the state of the button from Easy to Medium to Hard
        difficulty.addActionListener(e -> {
            if(difficulty.getText().equals("Difficult : EASY")) {
                difficulty.setText("Difficult : MEDIUM");
            }else if (difficulty.getText().equals("Difficult : MEDIUM")) {
                difficulty.setText("Difficult : HARD");
            }else if (difficulty.getText().equals("Difficult : HARD")) {
                difficulty.setText("Difficult : EASY");
            }
            this.saveSettings();
        });
    }

/* ------------------------ SETTINGS PAGE GETTER -------------------------*/
    
        /**
        * @return the exit
        */
        public JButton getExit() {
            return exit;
        }
    
        /**
        * @return the returnBack
        */
        public JButton getReturnBack() {
            return returnBack;
        }
    
        /**
        * @return the audio
        */
        public boolean getAudioState() {
            if(audio.getText().equals("Audio ON")){
                return true;
            }else{
                return false;
            }
        }
    
        /**
        * @return the difficulty
        */
        public String getDifficulty() {
            return difficulty.getText();
        }

/* ------------------------ SETTINGS PAGE SETTER -------------------------*/

        /**
         * Load the settings from the file
         * @throws FileNotFoundException
         */
        public void loadSettings(){
                try{
                GameSettingsImpl settings = new GameSettingsImpl();
                //Audio settings
                audio.setText(settings.getValue("audio"));
                System.out.println(settings.getValue("audio"));
                //Difficulty settings
                difficulty.setText(settings.getValue("difficulty"));
                System.out.println(settings.getValue("difficulty"));

                //TODO: add more settings
            
            }catch(FileNotFoundException e){
                System.out.println("File not found");
            }

        }

        /**
         * Save the settings to the file
         */
        public void saveSettings() {
            try{
                GameSettingsImpl settings = new GameSettingsImpl();
                //Audio settings
                settings.setValue("audio", audio.getText());

                //Difficulty settings
                settings.setValue("difficulty", difficulty.getText());

                settings.writeSettings();
            }catch(FileNotFoundException e){
                System.out.println("File not found");
            } 
        }


        /**
        * @param audio the audio to set
        */
        public void setAudio(boolean audio) {
            if(audio){
                this.audio.setText("Audio ON");
            }else{
                this.audio.setText("Audio OFF");
            }
            this.saveSettings();
        }
    
        /**
        * @param difficulty the difficulty to set
        */
        public void setDifficulty(String difficulty) {
            this.difficulty.setText(difficulty);
            this.saveSettings();
        }

        /**
         * Set the JPanel visible or not
         * @param b
         */
        public void setVisible(boolean b) {
            this.setVisible(b);
        }

}
