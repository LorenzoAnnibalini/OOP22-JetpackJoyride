package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.core.api.SkinInfoPositions;
import it.unibo.jetpackjoyride.core.impl.SliderImpl;
import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.api.Orientation;
import it.unibo.jetpackjoyride.model.api.Scientist;
import it.unibo.jetpackjoyride.model.api.SkinInfo;
import it.unibo.jetpackjoyride.model.impl.GameObject;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;
import it.unibo.jetpackjoyride.model.impl.SkinInfoImpl;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;
import it.unibo.jetpackjoyride.model.impl.Money;
import it.unibo.jetpackjoyride.model.impl.Electrode;
import it.unibo.jetpackjoyride.model.impl.LaserRay;
import it.unibo.jetpackjoyride.model.impl.Rocket;

/**
 * Class of the panel's game. Used to visualize map of game and sprites.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public final class GamePanel extends JPanel {

    private Set<Pair<String, GameObject>> entities;
    private PlayerImpl player;
    private List<Money> money = new ArrayList<>();
    private int posImage1;
    private int posImage2;
    private Image backgruondImage1;
    private Image backgruondImage2;
    private Image rocket;
    private Image warning;
    private Image vertElectrode;
    private Image horElectrode;
    private Image shield;
    private Image speedup;
    private Image rightScientist;
    private Image leftScientist;
    private Image laser;
    private Image barry;
    private Image barryWoman;
    private Image barryUp;
    private Image barryWomanUp;
    private Image playerImage;
    private Image playerUpImage;
    private Image moneyImage;
    private SliderImpl slider;
    private int width;
    private int height;
    private int score = 0;
    private int monies = 0;
    private final String fileName = "/config/sprites.json";
    private JLabel scoreLabel;
    private JLabel moneyLabel;
    /**
     * Constant for the label's width.
     */
    private static final int LABEL_WIDTH = 100;
    /**
     * Constant for the label's height.
     */
    private static final int LABEL_HEIGHT = 20;
    /**
     * Constant for the shield's stroke.
     */
    private static final int SHIELD_STROKE = 5;
    /**
     * Constant for the shield's radius.
     */
    private static final int SHIELD_RADIUS = 70;
    /**
     * Constant for the shield's extremes.
     */
    private static final Pair<Integer, Integer> LASER_EXTREMES = new Pair<>(-10, 1150);

    /**
     * Constructor of the class.
     * 
     * @throws ParseException
     * @throws IOException
     */
    public GamePanel() throws ParseException, IOException {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        SpriteLoader spriteLoader = new SpriteLoader();
        spriteLoader.loadSprites(fileName);
        Map<String, Sprite> sprites = spriteLoader.getSpritesScaled();
        // loading background image
        backgruondImage1 = sprites.get("background1").getScaled();
        backgruondImage2 = sprites.get("background1").getScaled();
        this.width = sprites.get("background1").getScaledlDim().getX();
        this.height = sprites.get("background1").getScaledlDim().getY();
        slider = new SliderImpl(this.width);
        // loading sprite images and adjust sizes
        rocket = sprites.get("rocket").getScaled();
        warning = sprites.get("warning").getScaled();
        vertElectrode = sprites.get("vElectrode").getScaled();
        horElectrode = sprites.get("hElectrode").getScaled();
        shield = sprites.get("shield").getScaled();
        speedup = sprites.get("speedup").getScaled();
        rightScientist = sprites.get("rightScientist").getScaled();
        leftScientist = sprites.get("leftScientist").getScaled();
        laser = sprites.get("laser").getScaled();
        barry = sprites.get("barry").getScaled();
        barryUp = sprites.get("barryUp").getScaled();
        barryWoman = sprites.get("barryWoman").getScaled();
        barryWomanUp = sprites.get("barryWomanUp").getScaled();
        moneyImage = sprites.get("money").getScaled();
        // Stats labels
        this.scoreLabel = new JLabel("Score: " + score);
        this.moneyLabel = new JLabel("Monies: " + monies);
        Icon moneyIcon = new ImageIcon(moneyImage);
        this.moneyLabel.setIcon(moneyIcon);
        this.scoreLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        this.moneyLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        moneyLabel.setAlignmentX(LEFT_ALIGNMENT);
        moneyLabel.setAlignmentY(LEFT_ALIGNMENT);
        scoreLabel.setAlignmentX(LEFT_ALIGNMENT);
        scoreLabel.setAlignmentY(LEFT_ALIGNMENT);
        // Add components to panel and set size
        this.add(moneyLabel);
        this.add(scoreLabel);
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setSize(this.getPreferredSize());
        this.setVisible(false);
        this.posImage1 = 0;
        this.posImage2 = this.width;
        this.slider.start();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Update stats
        StatisticsImpl currentStats = this.player.getStatistics();
        this.monies = currentStats.getValue("GrabbedMoney");
        this.score = currentStats.getValue("TotalMeters");
        // Update labels
        this.moneyLabel.setText("Monies: " + this.monies);
        this.scoreLabel.setText("Score: " + this.score);
        // Draw background image
        g2d.drawImage(backgruondImage1, this.posImage1 - slider.getPos(), 0, this);
        g2d.drawImage(backgruondImage2, this.posImage2 - slider.getPos(), 0, this);
        // Draw entities
        for (Pair<String, GameObject> el : entities) {
            String entityName = el.getX();
            GameObject entity = el.getY();
            switch (entityName) {
                case "Rocket":
                    if (!((Rocket) entity).isActive()) {
                        this.drawSprite(g2d, warning, entity);
                    } else {
                        this.drawSprite(g2d, rocket, entity);
                    }
                    break;
                case "Electrode":
                    if (((Electrode) entity).getOrientation() == Orientation.HORIZONTAL) {
                        this.drawSprite(g2d, horElectrode, entity);
                    } else {
                        this.drawSprite(g2d, vertElectrode, entity);
                    }
                    break;
                case "Scientist":
                    if (((Scientist) entity).getDirection() == Direction.RIGHT) {
                        this.drawSprite(g2d, rightScientist, entity);
                    } else {
                        this.drawSprite(g2d, leftScientist, entity);
                    }
                    break;
                case "ShieldPowerUp":
                    this.drawSprite(g2d, shield, entity);
                    break;
                case "SpeedUpPowerup":
                    this.drawSprite(g2d, speedup, entity);
                    break;
                case "Laser":
                    if (!((LaserRay) entity).isActive()) {
                        this.drawSprite(g2d, laser, entity);
                    } else {
                        this.drawSprite(g2d, laser, entity);
                        g2d.setStroke(new BasicStroke(4f));
                        g2d.setColor(Color.RED);
                        g2d.drawLine(0, (int) entity.getCurrentPos().y, this.getWidth(),
                                (int) entity.getCurrentPos().y);
                    }
                    break;
                case "Nothing":
                    break;
                default:
                    break;
            }

        }

        // Draw player
        if (player.getCurrentVel().getY() > 0) {
            this.drawSprite(g2d, playerImage, player);
        } else {
            this.drawSprite(g2d, playerUpImage, player);
        }
        // Draw shield if present
        if (player.getHearts() == 2) {
            g2d.setStroke(new BasicStroke(SHIELD_STROKE));
            g2d.setColor(Color.GREEN);
            g2d.drawOval((int) player.getHitbox().getPointUpLeft().x - 10,
                    (int) player.getHitbox().getPointUpLeft().y - 10, SHIELD_RADIUS, SHIELD_RADIUS);
        }

        // Draw monies if present
        if (!money.isEmpty()) {
            for (Money m : money) {
                this.drawSprite(g2d, moneyImage, m);
            }
        }
    }

    /**
     * Metohd to draw a sprite.
     * 
     * @param g      graphics object
     * @param image  image to draw
     * @param entity entity object with values to draw
     */
    private void drawSprite(final Graphics2D g, final Image image, final GameObject entity) {
        // x and y are the coordinates of the upper left corner of the image based on hitbox
        int x = (int) entity.getHitbox().getPointUpLeft().x;
        int y = (int) entity.getHitbox().getPointUpLeft().y;
        if (entity.getClass().getName() == "it.unibo.jetpackjoyride.model.impl.Money") {
            g.drawImage(image, x, y, this);
        } else {
            if (entity.getClass().getName() == "it.unibo.jetpackjoyride.model.impl.LaserRay") {
                g.drawImage(image, LASER_EXTREMES.getX(), y, this);
                g.drawImage(image, LASER_EXTREMES.getY(), y, this);
            } else {
                g.drawImage(image, x, y, this);

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
        // Load the right image for the player based on the skin
        SkinInfo skinInfo = new SkinInfoImpl();
        String skin = skinInfo.getAll().entrySet().stream()
                .filter(x -> "true".equals(x.getValue().get(SkinInfoPositions.STATE.ordinal()))).findAny().get()
                .getKey();
        this.playerImage = "barry".equals(skin) ? this.barry : this.barryWoman;
        this.playerUpImage = "barry".equals(skin) ? this.barryUp : this.barryWomanUp;
    }

    /**
     * Method to set new value for money.
     * 
     * @param money money to draw
     */
    public void setMoney(final List<Money> money) {
        // this.money.clear();
        this.money = money;
    }

}
