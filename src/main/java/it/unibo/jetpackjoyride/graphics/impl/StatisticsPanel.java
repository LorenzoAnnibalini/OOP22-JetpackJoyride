package it.unibo.jetpackjoyride.graphics.impl;

import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.api.Input.typeInput;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.model.api.Statistics;

/**
 * Class to visualize the statistics of the game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class StatisticsPanel extends JPanel {
    private Statistics statistics;
    private Map<String, Integer> statsMap = new HashMap<>();
    private final JButton menu;
    private final InputQueue inputQueue;

    /**
     * Constructor of the class.
     * 
     * @param statistics a map from string (statistic name) to int (statistic value)
     * @param inputQueue the input queue
     */
    public StatisticsPanel(final InputQueue inputQueue, Statistics statistics) {
        super();
        this.statistics = statistics;
        this.setLayout(new BorderLayout());
        this.inputQueue = inputQueue;
        this.menu = new JButton("Menu");
        this.menu.addActionListener(e -> {
            this.inputQueue.addInput(new InputImpl(typeInput.MENU, null));
        });
        this.add(menu, BorderLayout.SOUTH);
    }

    public void update() {
        JPanel boxPanel = new JPanel(new FlowLayout());
        this.statsMap = this.statistics.getAll();
        String statsText = "<html>";
        for (String statName : this.statsMap.keySet()) {
            int value = this.statsMap.get(statName);
            statsText = statsText + statName + ": " + value + "<br>";
        }
        statsText = statsText + "</html>";
        JLabel label = new JLabel(statsText);
        boxPanel.add(label, BorderLayout.CENTER);
        this.add(boxPanel, BorderLayout.CENTER);
    }
}
