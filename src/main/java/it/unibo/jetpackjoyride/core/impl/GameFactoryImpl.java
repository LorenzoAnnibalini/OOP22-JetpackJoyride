package it.unibo.jetpackjoyride.core.impl;

import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.impl.Electrode;
import it.unibo.jetpackjoyride.model.impl.GameObject;
import it.unibo.jetpackjoyride.model.impl.LaserRay;
import it.unibo.jetpackjoyride.model.impl.Rocket;
import it.unibo.jetpackjoyride.model.impl.ScientistImpl;
import it.unibo.jetpackjoyride.model.impl.ShieldPowerUpImpl;
import it.unibo.jetpackjoyride.model.impl.SpeedUpPowerUpImpl;
import it.unibo.jetpackjoyride.model.impl.HitboxImpl;

import java.util.Random;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.core.api.GameFactory;
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
    public final Electrode createElectrode(final Set<Pair<String, GameObject>> entities) {
        final int y = this.getY(entities);
        final int orientation = random.nextInt(GameFactoryImpl.RANDOMSEED);
        if (orientation == GameFactoryImpl.HORIZONTAL) {
            this.hitbox = new HitboxImpl(RECTANGLEHITBOX.getY(), RECTANGLEHITBOX.getX(),
                    new Point2d(startPosition.getX(), startPosition.getY()));
        } else {
            this.hitbox = new HitboxImpl(RECTANGLEHITBOX.getX(), RECTANGLEHITBOX.getY(),
                    new Point2d(startPosition.getX(), startPosition.getY()));
        }
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND, y);
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        this.velocity = new Vector2d(finishPosition, startPosition);
        return new Electrode(this.startPosition, this.velocity, 
                orientation == GameFactoryImpl.HORIZONTAL ? Orientation.HORIZONTAL
                    : Orientation.VERTICAL,
                    this.hitbox);
    }

    @Override
    public final Rocket createRocket(final Set<Pair<String, GameObject>> entities) {
        final int y = this.getY(entities);
        Vector2d rocketVelocity;
        this.startPosition = new Point2d(GameFactoryImpl.ROCKETBOUND, y);
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        rocketVelocity = new Vector2d(
            new Point2d(0, startPosition.getY()),
            startPosition);
        this.hitbox = new HitboxImpl(SQUAREHITBOX / 2, SQUAREHITBOX, startPosition);
        return new Rocket(this.startPosition, rocketVelocity, this.hitbox);
    }

    @Override
    public final LaserRay createLaserRay(final Set<Pair<String, GameObject>> entities) {
        final int y = this.getY(entities);
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND / 2, y);
        this.finishPosition = startPosition;
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl(LASERRAYHEIGHT, XBOUND, startPosition);
        return new LaserRay(this.startPosition, this.velocity, this.hitbox);
    }

    @Override
    public final ShieldPowerUpImpl createShieldPowerUp(final Set<Pair<String, GameObject>> entities) {
        final int duration = random.nextInt(GameFactoryImpl.RANDOMSEED);
        final int y = this.getY(entities);
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND, y);
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl(SQUAREHITBOX, SQUAREHITBOX, startPosition);
        return new ShieldPowerUpImpl(
                duration == GameFactoryImpl.DURATION ? GameFactoryImpl.SHORTDURATION
                        : GameFactoryImpl.LONGDURATION,
                this.startPosition, this.velocity, this.hitbox);
    }

    @Override
    public final SpeedUpPowerUpImpl createSpeedUpPowerUpImpl(final Set<Pair<String, GameObject>> entities) {
        final int distance = random.nextInt(GameFactoryImpl.XBOUND);
        final int y = this.getY(entities);
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND, y);
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl(SQUAREHITBOX, SQUAREHITBOX, startPosition);
        return new SpeedUpPowerUpImpl(distance, this.startPosition, this.velocity, this.hitbox);
    }

    @Override
    public final ScientistImpl createScientist(final Set<Pair<String, GameObject>> entities) {
        final int direction = random.nextInt(2);
        this.startPosition = new Point2d(
                direction == GameFactoryImpl.LEFT ? GameFactoryImpl.XBOUND : 0,
                GameFactoryImpl.YBOUND);
        this.finishPosition = new Point2d(
                direction == GameFactoryImpl.RIGHT ? GameFactoryImpl.SCIENTISTIMIT
                        : GameFactoryImpl.XBOUND - GameFactoryImpl.SCIENTISTIMIT,
                GameFactoryImpl.YBOUND);
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl(SQUAREHITBOX, SCIENTISTWIDTH, startPosition);
        return new ScientistImpl(
                direction == GameFactoryImpl.LEFT ? Direction.LEFT
                        : Direction.RIGHT,
                this.startPosition, this.velocity, this.hitbox);
    }

    @Override
    public final GameObject createGenericGameObject(final Set<Pair<String, GameObject>> entities) {
        final int y = this.getY(entities);
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND, y);
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl(NOTHINGHITBOX, NOTHINGHITBOX, startPosition);
        return new GameObject(this.startPosition, this.velocity, this.hitbox);
    }

    /**
     * Method to check if new entities has y like others already spawned.
     * 
     * @param y the y value of new entitiy
     * @param entities the set of entities already spawned
     * @return true if y is like someone's else, false otherwise
     */
    private boolean checkY(final int y, final Set<Pair<String, GameObject>> entities) {
        return entities.stream()
                .filter(x -> 
                        x.getY().getCurrentPos().getY() - y > -SPAWNRANGE
                        && x.getY().getCurrentPos().getY() - y < SPAWNRANGE)
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
