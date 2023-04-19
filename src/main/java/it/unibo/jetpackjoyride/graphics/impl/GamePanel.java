package it.unibo.jetpackjoyride.graphics.impl;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import it.unibo.jetpackjoyride.model.impl.EntitiesGenerationImpl;
import it.unibo.jetpackjoyride.model.impl.GameObject;
import it.unibo.jetpackjoyride.model.impl.Rocket;

/**
 * Class of the panel's game. Used to visualize map of game and sprites.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class GamePanel extends JPanel {

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
     * 
     * @param entities model object that creates entities
     */
    public GamePanel(final EntitiesGenerationImpl e) {
        this.entities = e;

        try {
            // loading background image
            backgruondImage1 = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "sfondo.jpg"));
            backgruondImage2 = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "sfondo.jpg"));
            // loading sprite images and adjust sizes
            rocket = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "rocket.png"));
            rocket = rocket.getScaledInstance(20, 30, Image.SCALE_SMOOTH);
            electrode = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "electrode.png"));
            electrode = rocket.getScaledInstance(20, 30, Image.SCALE_SMOOTH);
            powerup = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "powerup.png"));
            powerup = rocket.getScaledInstance(20, 30, Image.SCALE_SMOOTH);
            scientist = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "scientist.png"));
            scientist = rocket.getScaledInstance(20, 30, Image.SCALE_SMOOTH);
            laser = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "laser.png"));
            laser = rocket.getScaledInstance(20, 30, Image.SCALE_SMOOTH);
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
    @Override
    protected void paintComponent(Graphics g) {
        g = (Graphics2D) g;
        super.paintComponent(g);
        g.drawImage(backgruondImage1, this.posImage1 - slider.getPos(), 0, this);
        g.drawImage(backgruondImage2, this.posImage2 - slider.getPos(), 0, this);
        for (Pair<String, GameObject> el : entities.getEntities()) {
            switch(el.getX()){
                case "Rocket":
                    /*g.drawImage(rocket, el.getY().getCurrentPos().x, el.getY().getCurrentPos().x, this);
                    this.drawSprite(g, rocket, el);*/
                    g.drawImage(rocket, el.getY().getCurrentPos().x, el.getY().getCurrentPos().x, this);
                    break;
                case "Electrode":
                    break;
                case "Scientist":
                    break;
                case "Powerup":
                    break;
                case "Laser":
                    break;
            }

        }
        //g.drawImage(rocket, entities.getEntities.get, 50, this);
        
    }

    /*private void drawSprite(Graphics g, Image sprite, GameObject entity) {
        g.drawImage(sprite, entity.getCurrentPos().x, entity.getCurrentPos().x, this);
    }*/
}
