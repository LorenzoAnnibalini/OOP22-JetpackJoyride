package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.api.GameFactory;
import it.unibo.jetpackjoyride.model.api.GameObject;

import java.util.Random;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.Orientation;

/**
 * Class to create the game objects.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class GameFactoryImpl implements GameFactory {

    private static final int DURATION = 0;
    private static final long SHORTDURATION = 5000;
    private static final long LONGDURATION = 8000;
    private static final int YBOUND = 530;
    private static final int XBOUND = 1180;
    private static final int ROCKETBOUND = 1100;
    private static final int LIMIT = GameFactoryImpl.XBOUND - 389;
    private static final int SCIENTISTIMIT = GameFactoryImpl.XBOUND - 500;
    private static final int HORIZONTAL = 0;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int RANDOMSEED = 2;
    private static final int SQUAREHITBOX = 50;
    private static final Pair<Integer, Integer> RECTANGLEHITBOX = new Pair<>(100, 25);
    private static final int SCIENTISTWIDTH = 30;
    private static final int LASERRAYHEIGHT = 30;
    private static final int NOTHINGHITBOX = 0;
    private static final int SPAWNRANGE = 50;
    private static final int XRANGE = 20;

    private final Random random;
    private Point2d startPosition;
    private Point2d finishPosition;
    private Vector2d velocity;

    private HitboxImpl hitbox;

    /**
     * Constructor of GameFactoryImpl.
     */
    public GameFactoryImpl() {
        this.random = new Random();
    }

    @Override
    public final GameObject createElectrode(final Set<Pair<String, GameObject>> entities) {
        final int y = this.getY(entities);
        final int orientation = random.nextInt(GameFactoryImpl.RANDOMSEED);
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND + XRANGE - random.nextInt(10), y);
        if (orientation == GameFactoryImpl.HORIZONTAL) {
            this.hitbox = new HitboxImpl(Double.valueOf(RECTANGLEHITBOX.getY()), Double.valueOf(RECTANGLEHITBOX.getX()),
                    new Point2d(startPosition.getX(), startPosition.getY()));
        } else {
            this.hitbox = new HitboxImpl(Double.valueOf(RECTANGLEHITBOX.getX()), Double.valueOf(RECTANGLEHITBOX.getY()),
                    new Point2d(startPosition.getX(), startPosition.getY()));
        }
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        this.velocity = new Vector2d(finishPosition, startPosition);
        return new Electrode(this.startPosition, this.velocity,
                orientation == GameFactoryImpl.HORIZONTAL ? Orientation.HORIZONTAL
                        : Orientation.VERTICAL,
                this.hitbox);
    }

    @Override
    public final GameObject createRocket(final Set<Pair<String, GameObject>> entities) {
        final int y = this.getY(entities);
        Vector2d rocketVelocity;
        this.startPosition = new Point2d(GameFactoryImpl.ROCKETBOUND, y);
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        rocketVelocity = new Vector2d(
                new Point2d(0, startPosition.getY()),
                startPosition);
        this.hitbox = new HitboxImpl((double) SQUAREHITBOX / 2.0, (double) SQUAREHITBOX, startPosition);
        return new Rocket(this.startPosition, rocketVelocity, this.hitbox);
    }

    @Override
    public final GameObject createLaserRay(final Set<Pair<String, GameObject>> entities) {
        final int y = this.getY(entities);
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND / 2.0, y);
        this.finishPosition = startPosition;
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl((double) LASERRAYHEIGHT, (double) XBOUND, startPosition);
        return new LaserRay(this.startPosition, this.velocity, this.hitbox);
    }

    @Override
    public final GameObject createShieldPowerUp(final Set<Pair<String, GameObject>> entities) {
        final int duration = random.nextInt(GameFactoryImpl.RANDOMSEED);
        final int y = this.getY(entities);
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND, y);
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl((double) SQUAREHITBOX, (double) SQUAREHITBOX, startPosition);
        return new ShieldPowerUpImpl(
                duration == GameFactoryImpl.DURATION ? GameFactoryImpl.SHORTDURATION
                        : GameFactoryImpl.LONGDURATION,
                this.startPosition, this.velocity, this.hitbox);
    }

    @Override
    public final GameObject createSpeedUpPowerUpImpl(final Set<Pair<String, GameObject>> entities) {
        final int distance = random.nextInt(GameFactoryImpl.XBOUND);
        final int y = this.getY(entities);
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND, y);
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl((double) SQUAREHITBOX, (double) SQUAREHITBOX, startPosition);
        return new SpeedUpPowerUpImpl(distance, this.startPosition, this.velocity, this.hitbox);
    }

    @Override
    public final GameObject createScientist(final Set<Pair<String, GameObject>> entities) {
        final int direction = random.nextInt(2);
        this.startPosition = new Point2d(
                direction == GameFactoryImpl.LEFT ? GameFactoryImpl.XBOUND : 0,
                GameFactoryImpl.YBOUND);
        this.finishPosition = new Point2d(
                direction == GameFactoryImpl.RIGHT ? GameFactoryImpl.SCIENTISTIMIT
                        : GameFactoryImpl.XBOUND - GameFactoryImpl.SCIENTISTIMIT,
                GameFactoryImpl.YBOUND);
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl((double) SQUAREHITBOX, (double) SCIENTISTWIDTH, startPosition);
        return new ScientistImpl(
                direction == GameFactoryImpl.LEFT ? Direction.LEFT
                        : Direction.RIGHT,
                this.startPosition, this.velocity, this.hitbox);
    }

    @Override
    public final GameObject createGenericGameObject(final Set<Pair<String, GameObject>> entities) {
        final int y = this.getY(entities);
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND + XRANGE - random.nextInt(10), y);
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl((double) NOTHINGHITBOX, (double) NOTHINGHITBOX, startPosition);
        return new GameObjectImpl(this.startPosition, this.velocity, this.hitbox);
    }

    /**
     * Method to check if new entities has y like others already spawned.
     * 
     * @param y        the y value of new entitiy
     * @param entities the set of entities already spawned
     * @return true if y is like someone's else, false otherwise
     */
    private boolean checkY(final int y, final Set<Pair<String, GameObject>> entities) {
        return entities.stream()
                .filter(x -> (int) x.getY().getCurrentPos().getY() - y > -SPAWNRANGE
                        && (int) x.getY().getCurrentPos().getY() - y < SPAWNRANGE
                        && GameFactoryImpl.XBOUND - x.getY().getCurrentPos().getX() > XRANGE)
                .count() != 0;
    }

    /**
     * Method to get the y coordinate of a gameObject.
     * this method also check if the y is like someone's else
     * and only return "free" y.
     * 
     * @param entities the set of entities already spawned
     * @return the y coordinate
     */
    private int getY(final Set<Pair<String, GameObject>> entities) {
        int y = random.nextInt(GameFactoryImpl.YBOUND);
        while (checkY(y, entities)) {
            y = random.nextInt(GameFactoryImpl.YBOUND);
        }
        return y;
    }
}
