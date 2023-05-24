package it.unibo.jetpackjoyride.graphics.impl;

import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.core.impl.SavesImpl;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.api.Input.TypeInput;
import it.unibo.jetpackjoyride.input.impl.InputImpl;

/**
 * Class to visualize the statistics of the game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class StatisticsPanel extends JPanel {
    private final InputQueue inputQueue;
    private final Saves saves;

    /**
     * Constructor of the class.
     * 
     * @param inputQueue the input queue
     */
    public StatisticsPanel(final InputQueue inputQueue) {
        super();
        this.setLayout(new BorderLayout());
        this.inputQueue = inputQueue;
        final JButton menu;
        menu = new JButton("Menu");
        menu.addActionListener(e -> {
            this.inputQueue.addInput(new InputImpl(TypeInput.MENU, null));
        });
        this.add(menu, BorderLayout.SOUTH);
        saves = new SavesImpl();
    }

    public void update() throws FileNotFoundException, IOException {
        Map<String, Integer> statsMap = new HashMap<>();
        JPanel boxPanel = new JPanel(new FlowLayout());
        statsMap = saves.downloadSaves();
        String statsText = "<html>";
        for (String stat : statsMap.keySet()) {
            int value = statsMap.get(stat);
            String statName = stat;
            statsText = statsText + statName + ": " + value + "<br>";
        }
        statsText = statsText + "</html>";
        JLabel label = new JLabel(statsText);
        boxPanel.add(label, BorderLayout.CENTER);
        this.add(boxPanel, BorderLayout.CENTER);
    }
}
