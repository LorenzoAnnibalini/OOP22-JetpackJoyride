package it.unibo.jetpackjoyride.graphics.impl;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.core.api.Slider;
import it.unibo.jetpackjoyride.core.impl.SliderImpl;
import it.unibo.jetpackjoyride.model.impl.EntitiesGeneratorImpl;
import it.unibo.jetpackjoyride.model.impl.GameObject;
//import it.unibo.jetpackjoyride.model.impl.Rocket;

/**
 * Class of the panel's game. Used to visualize map of game and sprites.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class GamePanel extends JPanel {

    private final EntitiesGeneratorImpl entities;
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
    private Slider slider;

    /**
     * Constructor of the class.
     * 
     * @param entities model object that creates entities
     */
    public GamePanel (final EntitiesGeneratorImpl e) {
        this.entities = e;
        try {
            // loading background image
            backgruondImage1 = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "sfondo.jpg"));
            backgruondImage2 = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "sfondo.jpg"));
            slider = new SliderImpl(backgruondImage1.getWidth());
            // loading sprite images and adjust sizes
            rocket = this.loadImage("rocket.png");
            electrode = this.loadImage("electrode.png");
            powerup = this.loadImage("powerup.png");
            scientist = this.loadImage("scientist.png");
            laser = this.loadImage("laser.png");
            this.posImage1 = 0;
            this.posImage2 = backgruondImage2.getWidth();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setPreferredSize(new Dimension(backgruondImage1.getWidth(), backgruondImage1.getHeight()));

        this.setSize(this.getPreferredSize());
        this.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        g = (Graphics2D) g;
        super.paintComponent(g);
        g.drawImage(backgruondImage1, this.posImage1 - slider.getPos(), 0, this);
        g.drawImage(backgruondImage2, this.posImage2 - slider.getPos(), 0, this);
        for (Pair<String, GameObject> el : entities.getEntities()) {
            String entityName = el.getX();
            GameObject entity = el.getY();
            switch (entityName) {
                case "Rocket":
                    this.drawSprite(g, rocket, entity);
                    break;
                case "Electrode":
                    this.drawSprite(g, electrode, entity);
                    break;
                case "Scientist":
                    this.drawSprite(g, scientist, entity);
                    break;
                case "Powerup":
                    this.drawSprite(g, powerup, entity);
                    break;
                case "Laser":
                    this.drawSprite(g, laser, entity);
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * Method to load and scale a sprite's image
     * @param filename the name of the file 
     * @return a new image already scaled based on constant values of the class
     * @throws IOException if the file doesn't exists
     */
    private Image loadImage(String filename) throws IOException {
        BufferedImage buffImage = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + filename));
        return buffImage.getScaledInstance(20, 30, Image.SCALE_SMOOTH);
    }

    /**
     * Metohd to draw a sprite.
     * @param g graphics object
     * @param sprite image to draw
     * @param entity entity object with values to draw
     */
    private void drawSprite(Graphics g, Image sprite, GameObject entity) {
        g.drawImage(sprite, (int) entity.getCurrentPos().x, (int) entity.getCurrentPos().x, this);
    }
}
