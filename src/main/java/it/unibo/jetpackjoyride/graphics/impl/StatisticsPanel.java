package it.unibo.jetpackjoyride.graphics.impl;

import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Class to visualize the statistics of the game
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class StatisticsPanel extends JPanel {
    private Map<String, Integer> statistics;
    private BufferedImage backgruondImage;
    private static final String FILESEPARATOR = File.separator;
    JTextArea statsVisualizer;

    /**
     * Constructor of the class.
     * 
     * @param statistics a map from string (statistic name) to integer (value of the
     *                   statistic)
     */
    public StatisticsPanel(Map<String, Integer> statistics) {
        this.statistics = new HashMap<>(statistics);
        try{
            this.backgruondImage = ImageIO.read(new File("resources" + StatisticsPanel.FILESEPARATOR + "sfondo.jpg"));
        } catch (IOException ex){
            ex.printStackTrace();
        }
        statsVisualizer = new JTextArea();

        this.add(statsVisualizer);

        this.setPreferredSize(new Dimension(backgruondImage.getWidth(), backgruondImage.getHeight()));
        this.setSize(this.getPreferredSize());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(backgruondImage, 0, 0, this);
        statsVisualizer.removeAll();
        for (String statName : this.statistics.keySet()) {
            int value = statistics.get(statName);
            String text = statName + "\t\t\t\t " + value;
            this.add(new JLabel(text));
            
        }
    }
}
