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
import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.core.api.Slider;
import it.unibo.jetpackjoyride.core.impl.SliderImpl;
import it.unibo.jetpackjoyride.model.impl.GameObject;
//import it.unibo.jetpackjoyride.model.impl.Rocket;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;

/**
 * Class of the panel's game. Used to visualize map of game and sprites.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class GamePanel extends JPanel {

    private final Set<Pair<String, GameObject>> entities;
    private final PlayerImpl player;
    private int posImage1;
    private int posImage2;
    private BufferedImage backgruondImage1;
    private BufferedImage backgruondImage2;
    private Image rocket;
    private Image electrode;
    private Image powerup;
    private Image scientist;
    private Image laser;
    private Image playerImage;
    private Slider slider;
    private static final String FILESEPARATOR = File.separator;
    private static final int SPRITEWIDTH = 30;
    private static final int SPRITEHEIGHT = 30;

    /**
     * Constructor of the class.
     * 
     * @param entities the set of entities to draw
     */
    public GamePanel(final Set<Pair<String, GameObject>> entities, PlayerImpl player) {
        this.entities = entities;
        this.player = player;
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
            playerImage = this.loadImage("player.png");
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
        // Draw background image
        g.drawImage(backgruondImage1, this.posImage1 - slider.getPos(), 0, this);
        g.drawImage(backgruondImage2, this.posImage2 - slider.getPos(), 0, this);
        // Draw entities
        for (Pair<String, GameObject> el : entities) {
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
                case "Nothing":
                    break;
                default:
                    break;
            }

        }
        // Draw player
        this.drawSprite(g, playerImage, player);
    }

    /**
     * Method to load and scale a sprite's image
     * 
     * @param filename the name of the file
     * @return a new image already scaled based on constant values of the class
     * @throws IOException if the file doesn't exists
     */
    private Image loadImage(String filename) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + filename));
        return originalImage.getScaledInstance(GamePanel.SPRITEWIDTH, GamePanel.SPRITEHEIGHT, Image.SCALE_SMOOTH);
    }

    /**
     * Metohd to draw a sprite.
     * 
     * @param g      graphics object
     * @param sprite image to draw
     * @param entity entity object with values to draw
     */
    private void drawSprite(Graphics g, Image image, GameObject entity) {
        g.drawImage(image, (int) entity.getCurrentPos().x, (int) entity.getCurrentPos().x, this);
    }
}
