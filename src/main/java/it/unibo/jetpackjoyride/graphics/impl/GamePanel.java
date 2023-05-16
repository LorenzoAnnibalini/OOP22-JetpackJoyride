package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.core.api.Slider;
import it.unibo.jetpackjoyride.core.impl.SliderImpl;
import it.unibo.jetpackjoyride.input.api.Input;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.model.impl.GameObject;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;
import it.unibo.jetpackjoyride.model.impl.Money;

/**
 * Class of the panel's game. Used to visualize map of game and sprites.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class GamePanel extends JPanel implements KeyListener {

    private Set<Pair<String, GameObject>> entities;
    private PlayerImpl player;
    private List<Money> money = new ArrayList<>();
    private InputQueue inputHandler;
    private boolean isPressed;
    private int posImage1;
    private int posImage2;
    private Image backgruondImage1;
    private Image backgruondImage2;
    private Image rocket;
    private Image electrode;
    private Image shield;
    private Image speedup;
    private Image rightScientist;
    private Image leftScientist;
    private Image laserOn;
    private Image laserOff;
    private Image playerImage;
    private Image moneyImage;
    private SliderImpl slider;
    private int width;
    private int height;
    private static final String filename = "/config/sprites.json";

    /**
     * Constructor of the class.
     * 
     * @param entities the set of entities to show
     * @param player   the object of the player
     * @param money    the list of money that has to be shown
     * @throws ParseException
     */
    public GamePanel(final Set<Pair<String, GameObject>> entities, final PlayerImpl player, final List<Money> money,final InputQueue inputHandler)
            throws ParseException {
        this.entities = entities;
        this.player = player;
        this.money.addAll(money);
        this.inputHandler = inputHandler;
        this.isPressed = false;
        SpriteLoader spriteLoader = new SpriteLoader();
        addKeyListener(this);
        spriteLoader.loadSprites(filename);
        Map<String, Sprite> sprites = spriteLoader.getSpritesScaled();
        // loading background image
        backgruondImage1 = sprites.get("background").getScaled();
        backgruondImage2 = sprites.get("background").getScaled();
        this.width = sprites.get("background").getScaledlDim().getX();
        this.height = sprites.get("background").getScaledlDim().getY();
        slider = new SliderImpl(this.width);
        // loading sprite images and adjust sizes
        rocket = sprites.get("rocket").getScaled();
        electrode = sprites.get("electrode").getScaled();
        shield = sprites.get("shield").getScaled();
        speedup = sprites.get("speedup").getScaled();
        rightScientist = sprites.get("rightScientist").getScaled();
        leftScientist = sprites.get("leftScientist").getScaled();
        laserOn = sprites.get("laserOn").getScaled();
        laserOff = sprites.get("laserOff").getScaled();
        playerImage = sprites.get("player").getScaled();
        moneyImage = sprites.get("money").getScaled();
        this.posImage1 = 0;
        this.posImage2 = this.width;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setSize(this.getPreferredSize());
        this.setVisible(true);
        this.slider.start();
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
                case "rightScientist":
                    this.drawSprite(g, rightScientist, entity);
                    break;
                case "leftScientist":
                    this.drawSprite(g, leftScientist, entity);
                    break;
                case "Shield":
                    this.drawSprite(g, shield, entity);
                    break;
                case "Speedup":
                    this.drawSprite(g, speedup, entity);
                    break;
                case "LaserOn":
                    this.drawSprite(g, laserOn, entity);
                    break;
                case "LaserOff":
                    this.drawSprite(g, laserOff, entity);
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
        if (!money.isEmpty()) {
            for (Money m : money) {
                this.drawSprite(g, moneyImage, m);
            }
        }
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

    @Override
    public void keyPressed(final KeyEvent e) {

        if (e.getKeyCode() == 32 && this.isPressed!=true) {
            this.isPressed = true;
            this.inputHandler.addInput(new InputImpl(Input.typeInput.UP_PRESSED,"UP_PRESSED"));
        }
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() == 32 && this.isPressed!=false) {
            this.isPressed = false;
            this.inputHandler.addInput(new InputImpl(Input.typeInput.UP_RELEASED,"UP_RELEASED"));
        }
    }
}
