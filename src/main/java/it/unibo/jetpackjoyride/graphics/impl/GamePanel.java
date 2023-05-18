package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.core.api.SkinInfoPositions;
import it.unibo.jetpackjoyride.core.api.Slider;
import it.unibo.jetpackjoyride.core.impl.SliderImpl;
import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.api.Orientation;
import it.unibo.jetpackjoyride.model.api.Scientist;
import it.unibo.jetpackjoyride.model.api.SkinInfo;
import it.unibo.jetpackjoyride.model.impl.GameObject;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;
import it.unibo.jetpackjoyride.model.impl.SkinInfoImpl;
import it.unibo.jetpackjoyride.model.impl.Money;
import it.unibo.jetpackjoyride.model.impl.Electrode;

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
    private Image backgruondImage1;
    private Image backgruondImage2;
    private Image rocket;
    private Image vertElectrode;
    private Image horElectrode;
    private Image shield;
    private Image speedup;
    private Image rightScientist;
    private Image leftScientist;
    private Image laser;
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
    public GamePanel() throws ParseException {
        SpriteLoader spriteLoader = new SpriteLoader();
        spriteLoader.loadSprites(filename);
        Map<String, Sprite> sprites = spriteLoader.getSpritesScaled();
        // loading background image
        backgruondImage1 = sprites.get("background").getScaled();
        backgruondImage2 = sprites.get("background").getScaled();
        this.width = sprites.get("background").getScaledlDim().getX();
        this.height = sprites.get("background").getScaledlDim().getY();
        slider = new SliderImpl(this.width);
        // loading sprite images and adjust sizes
        Image barry;
        Image barryWoman;
        rocket = sprites.get("rocket").getScaled();
        vertElectrode = sprites.get("vElectrode").getScaled();
        horElectrode = sprites.get("hElectrode").getScaled();
        shield = sprites.get("shield").getScaled();
        speedup = sprites.get("speedup").getScaled();
        rightScientist = sprites.get("rightScientist").getScaled();
        leftScientist = sprites.get("leftScientist").getScaled();
        laser = sprites.get("laser").getScaled();
        barry = sprites.get("barry").getScaled();
        barryWoman = sprites.get("barryWoman").getScaled();
        moneyImage = sprites.get("money").getScaled();
        SkinInfo skinInfo = new SkinInfoImpl();
        String skin = skinInfo.getAll().entrySet().stream()
                .filter(x -> "true".equals(x.getValue().get(SkinInfoPositions.STATE.ordinal()))).findAny().get()
                .getKey();
        System.out.println(skin);
        playerImage = "barry".equals(skin) ? barry : barryWoman;
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
                    if (((Electrode) entity).getOrientation() == Orientation.HORIZONTAL) {
                        this.drawSprite(g, vertElectrode, entity);
                    } else {
                        this.drawSprite(g, horElectrode, entity);
                    }
                    break;
                case "Scientist":
                    if (((Scientist) entity).getDirection() == Direction.RIGHT) {
                        this.drawSprite(g, rightScientist, entity);
                    } else {
                        this.drawSprite(g, leftScientist, entity);
                    }
                    break;
                case "Shield":
                    this.drawSprite(g, shield, entity);
                    break;
                case "Speedup":
                    this.drawSprite(g, speedup, entity);
                    break;
                case "LaserOff":
                    this.drawSprite(g, laser, entity);
                    break;
                case "LaserOn":
                    this.drawSprite(g, laser, entity);
                    g.drawLine(0, (int) entity.getCurrentPos().y, this.getWidth(), (int) entity.getCurrentPos().y);
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
            this.drawSprite(g, moneyImage, money.get(0));
            System.out.println(money.get(0).getCurrentPos());
            /*for (Money m : money) {
                this.drawSprite(g, moneyImage, m);
                System.out.println(m.getCurrentPos());
            }*/
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
        if (entity.getClass().getName() == "it.unibo.jetpackjoyride.model.impl.Money") {
            g.drawImage(image, (int) entity.getCurrentPos().x + this.getSize().width, (int) entity.getCurrentPos().y,
                    this);
        } else {
            if (entity.getClass().getName() == "it.unibo.jetpackjoyride.model.impl.Laser") {
                g.drawImage(image, 0, (int) entity.getCurrentPos().y, this);
                g.drawImage(image, this.getWidth(), (int) entity.getCurrentPos().y, this);
            } else {
                g.drawImage(image, (int) entity.getCurrentPos().x, (int) entity.getCurrentPos().y, this);
            }

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
        //this.money.clear();
        this.money = money;
    }

}
