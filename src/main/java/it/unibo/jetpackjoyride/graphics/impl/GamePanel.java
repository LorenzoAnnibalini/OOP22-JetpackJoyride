package it.unibo.jetpackjoyride.graphics.impl;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import it.unibo.jetpackjoyride.model.impl.EntitiesGenerationImpl;

/**
 * Class of the panel's game. Used to visualize map of game and sprites.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class GamePanel extends JFrame {

    private final EntitiesGenerationImpl entities;
    private int posImage1;
    private int posImage2;
    private BufferedImage backgruondImage1;
    private BufferedImage backgruondImage2;
    private Image rocket;
    private Image electrode;
    private Image powerup;
    private Image scientist;
    private Image laser;
    private static final String FILESEPARATOR = File.separator;

    /**
     * Constructor of the class.
     * @param entities model object that creates entities
     */
    public GamePanel(final EntitiesGenerationImpl e) {
        this.entities = e;

        try {
            //loading background image
            backgruondImage1 = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "sfondo.jpg"));
            backgruondImage2 = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "sfondo.jpg"));
            //loading sprite images
            rocket = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "rocket.png"));
            electrode = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "electrode.png"));
            powerup = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "powerup.png"));
            scientist = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "scientist.png"));
            laser = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "laser.png"));
            rocket = rocket.getScaledInstance(20, 30, Image.SCALE_SMOOTH);
            this.posImage1 = 0;
            this.posImage2 = backgruondImage2.getWidth();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setPreferredSize(new Dimension(backgruondImage1.getWidth(), backgruondImage1.getHeight()));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(this.getPreferredSize());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
