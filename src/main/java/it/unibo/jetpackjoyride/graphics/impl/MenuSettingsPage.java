package it.unibo.jetpackjoyride.graphics.impl;

import it.unibo.jetpackjoyride.core.impl.GameSettingsImpl;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    private static final long serialVersionUID = 10L;

    //Constants
    static final String AUDIOON = "Audio ON";
    static final String AUDIOOFF = "Audio OFF";
    static final String EASY = "Difficult : EASY";
    static final String MEDIUM = "Difficult : MEDIUM";
    static final String HARD = "Difficult : HARD";

    // Settings panels
    private final JPanel settingsPageOption = new JPanel();
    private final JPanel settingsPageComands = new JPanel();

    // Title of the settings page
    private final JTextArea title = new JTextArea();
    private final JPanel titlePanel = new JPanel();
    static final int SIZE = 30;

    // Settings buttons
    private final JButton exit = new JButton("Exit");
    private final JButton returnBack = new JButton("Return");
    private final JButton audio = new JButton("Audio ON");
    private final JButton difficulty = new JButton("Easy");

    /**
     * Constructor of the settings page.
     */
    public MenuSettingsPage() throws FileNotFoundException, IOException {

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

        // It will change the state of the button from ON to OFF and viceversa
        this.audio.addActionListener(e -> {
            switch (audio.getText()) {
                case AUDIOON:
                    audio.setText(AUDIOOFF);
                    break;
                case AUDIOOFF:
                    audio.setText(AUDIOON);
                    break;
                default:
                    break;
            }
        });

        // It will change the state of the button from Easy to Medium to Hard
        difficulty.addActionListener(e -> {
            switch (difficulty.getText()) {
                case EASY:
                    difficulty.setText(MEDIUM);
                    break;
                case MEDIUM:
                    difficulty.setText(HARD);
                    break;
                case HARD:
                    difficulty.setText(EASY);
                    break;
                default:
                    break;
            }
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
     * @return the audio saved state
     */
    public boolean isAudioOn() {
        switch (audio.getText()) {
            case AUDIOON:
                return true;
            case AUDIOOFF:
                return false;
            default:
                return false;
        }
    }

    /**
     * @return the difficulty saved state
     */
    public String getDifficulty() {
        return difficulty.getText();
    }

    /* ------------------------ SETTINGS PAGE SETTER ------------------------- */

    /**
     * Load the settings from the file.
     * 
     * @throws FileNotFoundException
     */
    public void loadSettings() throws FileNotFoundException {
            final GameSettingsImpl settings = new GameSettingsImpl();
            // Audio settings
            audio.setText(settings.getValue("audio"));
            // Difficulty settings
            difficulty.setText(settings.getValue("difficulty"));
    }

    /**
     * Save the settings to the file.
     * 
     * @throws IOException
     */
    public void saveSettings() throws FileNotFoundException, IOException {
            final GameSettingsImpl settings = new GameSettingsImpl();
            // Audio settings
            settings.setValue("audio", audio.getText());

            // Difficulty settings
            settings.setValue("difficulty", difficulty.getText());

            settings.writeSettings();
    }

    /**
     * @param audio the audio to set
     */
    public void setAudio(final boolean audio) throws FileNotFoundException, IOException {
        if (audio) {
            this.audio.setText(AUDIOON);
        } else {
            this.audio.setText(AUDIOOFF);
        }
        this.saveSettings();
    }

    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(final String difficulty) throws FileNotFoundException, IOException {
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
