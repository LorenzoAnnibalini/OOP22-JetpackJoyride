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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.core.api.Slider;
import it.unibo.jetpackjoyride.core.impl.SliderImpl;
import it.unibo.jetpackjoyride.model.impl.GameObject;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;
import it.unibo.jetpackjoyride.model.impl.Money;

/**
 * Class of the panel's game. Used to visualize map of game and sprites.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class GamePanel extends JPanel {

    private Set<Pair<String, GameObject>> entities;
    private PlayerImpl player;
    private List<Money> money = new ArrayList<>();
    private int posImage1;
    private int posImage2;
    private BufferedImage backgruondImage1;
    private BufferedImage backgruondImage2;
    private Image rocket;
    private Image electrode;
    private Image shield;
    private Image speedup;
    private Image scientist;
    private Image laser;
    private Image playerImage;
    private Image moneyImage;
    private SliderImpl slider;
    private static final String FILESEPARATOR = File.separator;
    private static final int SPRITEWIDTH = 30;
    private static final int SPRITEHEIGHT = 30;

    /**
     * Constructor of the class.
     * 
     * @param entities the set of entities to show
     * @param player   the object of the player
     * @param money    the list of money that has to be shown
     */
    public GamePanel(final Set<Pair<String, GameObject>> entities, final PlayerImpl player, final List<Money> money) {
        this.entities = entities;
        this.player = player;

        //this.money.addAll(money);
        try {
            
            
            // loading background image
            /*backgruondImage1 = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "sfondo.jpg"));
            backgruondImage2 = ImageIO.read(new File("resources" + GamePanel.FILESEPARATOR + "sfondo.jpg"));*/
            backgruondImage1 = ImageIO.read(new File("C:\\Users\\manus\\Desktop\\Manu\\Scuola\\UNI\\2_anno\\OOP\\00PROGETTO\\OOP22-JetpackJoyride\\src\\main\\resources\\sfondo2.jpg"));
            backgruondImage2 = ImageIO.read(new File("C:\\Users\\manus\\Desktop\\Manu\\Scuola\\UNI\\2_anno\\OOP\\00PROGETTO\\OOP22-JetpackJoyride\\src\\main\\resources\\sfondo2.jpg"));
            
            String percorso = "C:\\Users\\manus\\Desktop\\Manu\\Scuola\\UNI\\2_anno\\OOP\\00PROGETTO\\OOP22-JetpackJoyride\\src\\main\\resources\\";
            
            slider = new SliderImpl(backgruondImage1.getWidth());
            this.slider.start();
            // loading sprite images and adjust sizes
            rocket = this.loadImage("C:\\Users\\manus\\Desktop\\Manu\\Scuola\\UNI\\2_anno\\OOP\\00PROGETTO\\OOP22-JetpackJoyride\\src\\main\\resources\\rocket.png");
            electrode = this.loadImage(percorso+"electrode.png");
            shield = this.loadImage(percorso+"shield.png");
            speedup = this.loadImage(percorso+"speedup.png");
            scientist = this.loadImage(percorso+"scientist.png");
            laser = this.loadImage(percorso+"laser.png");
            playerImage = this.loadImage(percorso+"player.png");
            moneyImage = this.loadImage(percorso+"money.png");
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
                case "Shield":
                    this.drawSprite(g, shield, entity);
                    break;
                case "Speedup":
                    this.drawSprite(g, speedup, entity);
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

        // Draw monies if present
        if (!this.money.isEmpty()) {
            for (Money m : money) {
                this.drawSprite(g, moneyImage, m);
            }
        }
    }

    /**
     * Method to load and scale a sprite's image
     * 
     * @param filename the name of the file
     * @return a new image already scaled based on constant values of the class
     * @throws IOException if the file doesn't exists
     */
    private Image loadImage(String filename) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(/*"resources" + GamePanel.FILESEPARATOR + */filename));
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
        if (entity.getClass().getName() == "Money") {
            g.drawImage(image, (int) entity.getCurrentPos().x + this.getSize().width, (int) entity.getCurrentPos().y,
                    this);
        } else {
            g.drawImage(image, (int) entity.getCurrentPos().x, (int) entity.getCurrentPos().y, this);
        }
    }

    /**
     * Method to set new values for entities.
     * 
     * @param entities entities to draw
     */
    public void setEntities(final Set<Pair<String, GameObject>> entities) {
        this.entities = entities;
    }

    /**
     * Method to set new value for the player.
     * 
     * @param player player to draw
     */
    public void setPlayer(final PlayerImpl player) {
        this.player = player;
    }

    /**
     * Method to set new value for money.
     * 
     * @param money money to draw
     */
    public void setMoney(final List<Money> money) {
        this.money.clear();
        this.money.addAll(money);
    }
}
