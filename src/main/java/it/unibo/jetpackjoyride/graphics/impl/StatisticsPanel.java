package it.unibo.jetpackjoyride.graphics.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
    private Map<String, Integer>statsMap = new HashMap<>();
    private static final String FONTNAME = "Verdana";
    private static final int FONTSTYLE = 0;
    private static final int FONTSIZE = 15;
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
        //System.out.println(this.statsMap.size());
        for (String statName : this.statsMap.keySet()) {
            int value = this.statsMap.get(statName);
            String text = statName + "\t\t\t\t " + value;
            JLabel label = new JLabel(text);
            label.setFont(new Font(StatisticsPanel.FONTNAME, StatisticsPanel.FONTSTYLE, StatisticsPanel.FONTSIZE));
            boxPanel.add(label, BorderLayout.CENTER);
            System.out.println(statName + " " + value);
        }
        this.add(boxPanel, BorderLayout.CENTER);
    }
}
