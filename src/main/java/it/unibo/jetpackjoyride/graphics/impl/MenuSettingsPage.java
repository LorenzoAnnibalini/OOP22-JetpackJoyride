package it.unibo.jetpackjoyride.graphics.impl;

import it.unibo.jetpackjoyride.core.impl.GameSettingsImpl;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;


/**
 * This class is used to create the settings page of the game.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class MenuSettingsPage extends JPanel {

    // Settings panels
    private JPanel settingsPageOption = new JPanel();
    private JPanel settingsPageComands = new JPanel();

    // Title of the settings page
    private JTextArea title = new JTextArea();
    private JPanel titlePanel = new JPanel();
    static final int SIZE = 30;

    // Settings buttons
    private JButton exit = new JButton("Exit");
    private JButton returnBack = new JButton("Return");
    private JButton audio = new JButton("Audio ON");
    private JButton difficulty = new JButton("Easy");

    public MenuSettingsPage() {

        // Settings Page layout
        this.setLayout(new BorderLayout());

        // Font of the title
        title.setEditable(false);
        title.setBackground(null);
        title.setFont(new Font("Arial", Font.BOLD, SIZE));
        titlePanel.add(title, Alignment.CENTER);
        final String settingsPageText = "Jetpack Joyride - Settings";
        title.setText(settingsPageText);

        // Position of the panels in the settingsPage
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.WEST);
        this.add(settingsPageOption, BorderLayout.CENTER);
        this.add(settingsPageComands, BorderLayout.SOUTH);

        // settingsPageOption panel
        settingsPageOption.setLayout(new GridLayout(2, 1));
        settingsPageOption.add(audio);
        settingsPageOption.add(difficulty);

        // settingsPageComand panel
        settingsPageComands.add(returnBack);
        settingsPageComands.add(exit);

        // Set visible to false
        this.setVisible(false);

        // Load the settings from the file
        loadSettings();

        // It will change the state of the button from ON to OFF and viceversa
        this.audio.addActionListener(e -> {
            if (audio.getText().equals("Audio ON")) {
                audio.setText("Audio OFF");
            } else {
                audio.setText("Audio ON");
            }
            this.saveSettings();
        });

        // It will change the state of the button from Easy to Medium to Hard
        difficulty.addActionListener(e -> {
            if (difficulty.getText().equals("Difficult : EASY")) {
                difficulty.setText("Difficult : MEDIUM");
            } else if (difficulty.getText().equals("Difficult : MEDIUM")) {
                difficulty.setText("Difficult : HARD");
            } else if (difficulty.getText().equals("Difficult : HARD")) {
                difficulty.setText("Difficult : EASY");
            }
            this.saveSettings();
        });
    }

    /* ------------------------ SETTINGS PAGE GETTER ------------------------- */

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
        if (audio.getText().equals("Audio ON")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the difficulty
     */
    public String getDifficulty() {
        return difficulty.getText();
    }

    /* ------------------------ SETTINGS PAGE SETTER ------------------------- */

    /**
     * Load the settings from the file
     * 
     * @throws FileNotFoundException
     */
    public void loadSettings() {
        try {
            final GameSettingsImpl settings = new GameSettingsImpl();
            // Audio settings
            audio.setText(settings.getValue("audio"));
            System.out.println(settings.getValue("audio"));
            // Difficulty settings
            difficulty.setText(settings.getValue("difficulty"));
            System.out.println(settings.getValue("difficulty"));

        } catch (final Exception e) {
            System.out.println(e.toString());
        }

    }

    /**
     * Save the settings to the file
     * 
     * @throws IOException
     */
    public void saveSettings() {
        try {
            final GameSettingsImpl settings = new GameSettingsImpl();
            // Audio settings
            settings.setValue("audio", audio.getText());

            // Difficulty settings
            settings.setValue("difficulty", difficulty.getText());

            settings.writeSettings();
        } catch (final Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * @param audio the audio to set
     */
    public void setAudio(final boolean audio) {
        if (audio) {
            this.audio.setText("Audio ON");
        } else {
            this.audio.setText("Audio OFF");
        }
        this.saveSettings();
    }

    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(final String difficulty) {
        this.difficulty.setText(difficulty);
        this.saveSettings();
    }

    /**
     * @param title the title to set
     */
    void setTitle(final String title) {
        this.title.setText(title);
    }
}
