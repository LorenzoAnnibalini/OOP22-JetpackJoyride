package it.unibo.jetpackjoyride.graphics.impl;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class is used to create the settings page of the game.
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class MenuSettingsPage {
    
    //Settings panels
    JPanel settingsPage = new JPanel(new BorderLayout());
    JPanel settingsPageOption = new JPanel();
    JPanel settingsPageComands = new JPanel();
    JButton exit = new JButton("Exit");
    JButton returnBack = new JButton("Return");
    JButton audio = new JButton("Audio ON");
    JButton difficulty = new JButton("Easy");

    public MenuSettingsPage() {
        //Position of the panels in the settingsPage
        settingsPage.add(new JPanel(), BorderLayout.EAST);
        settingsPage.add(new JPanel(), BorderLayout.WEST);
        settingsPage.add(settingsPageOption, BorderLayout.CENTER);
        settingsPage.add(settingsPageComands, BorderLayout.SOUTH);

        //settingsPageOption panel
        settingsPageOption.setLayout(new GridLayout(2,1));
        settingsPageOption.add(audio);
        settingsPageOption.add(difficulty);

        //settingsPageComand panel
        settingsPageComands.add(returnBack);
        settingsPageComands.add(exit);

        //Set visible to false
        settingsPage.setVisible(false);

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
            if(difficulty.getText().equals("Easy")) {
                difficulty.setText("Medium");
            }else if (difficulty.getText().equals("Medium")) {
                difficulty.setText("Hard");
            }else if (difficulty.getText().equals("Hard")) {
                difficulty.setText("Easy");
            }
            this.saveSettings();
        });
    }

/* ------------------------ SETTINGS PAGE GETTER -------------------------*/
    
        /**
        * @return the settingsPage
        */
        public JPanel getSettingsPage() {
            return settingsPage;
        }
    
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
            if(audio.getText().equals("Audio ON"))
                return true;
            else
                return false;
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
         */
        public void loadSettings() {
            //TODO: load settings from file
        }

        public void saveSettings() {
            //TODO: save settings to file
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
            settingsPage.setVisible(b);
        }

}
