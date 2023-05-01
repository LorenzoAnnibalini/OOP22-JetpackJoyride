package it.unibo.jetpackjoyride.graphics.impl;

import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class to visualize the statistics of the game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class StatisticsPanel extends JPanel {
    private Map<String, Integer> statistics;
    private BufferedImage backgruondImage;
    private static final String FILESEPARATOR = File.separator;
    private static final String FONTNAME = "Verdana";
    private static final int FONTSTYLE = 0;
    private static final int FONTSIZE = 15;

    /**
     * Constructor of the class.
     * 
     * @param statistics a map from string (statistic name) to integer (value of the
     *                   statistic)
     */
    public StatisticsPanel(Map<String, Integer> statistics) {
        this.statistics = new HashMap<>(statistics);
        try {
            this.backgruondImage = ImageIO.read(new File("resources" + StatisticsPanel.FILESEPARATOR + "sfondo.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.setPreferredSize(new Dimension(backgruondImage.getWidth(), backgruondImage.getHeight()));
        this.setSize(this.getPreferredSize());

        for (String statName : this.statistics.keySet()) {
            int value = statistics.get(statName);
            String text = statName + "\t\t\t\t " + value;
            JLabel label = new JLabel(text);
            label.setFont(new Font(StatisticsPanel.FONTNAME, StatisticsPanel.FONTSTYLE, StatisticsPanel.FONTSIZE));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.removeAll();
        g = (Graphics2D) g;
        g.drawImage(backgruondImage, 0, 0, this);
    }
}
