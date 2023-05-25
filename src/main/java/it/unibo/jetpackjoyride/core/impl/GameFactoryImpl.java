package it.unibo.jetpackjoyride.core.impl;

import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.impl.*;

import java.util.Random;
import java.util.Set;

import it.unibo.jetpackjoyride.common.*;
import it.unibo.jetpackjoyride.core.api.GameFactory;
import it.unibo.jetpackjoyride.model.api.Orientation;

public class GameFactoryImpl implements GameFactory{

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
    private static final Pair<Integer, Integer> RECTANGLEHITBOX = new Pair<Integer, Integer>(100, 25);
    private static final int SCIENTISTWIDTH = 30;
    private static final int NOTHINGHITBOX = 0;
    private static final int SPAWNRANGE = 50;
    
    static private GameFactoryImpl instance;
    private final Random random;
    private Point2d startPosition;
    private Point2d finishPosition;
    private Vector2d velocity;
    private Vector2d rocketVelocity;
    private HitboxImpl hitbox;


    static public GameFactoryImpl getInstance(){
        if(instance == null){
            instance = new GameFactoryImpl();
        }
        return instance;
    }

    public GameFactoryImpl(){
        this.random = new Random();
    }

    @Override
    public final Electrode createElectrode(Set<Pair<String, GameObject>> entities){
        int y = this.getY(entities);
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
    public final Rocket createRocket(Set<Pair<String, GameObject>> entities){
        int y = this.getY(entities);
        this.startPosition = new Point2d(GameFactoryImpl.ROCKETBOUND, y);
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        this.rocketVelocity = new Vector2d(
            new Point2d(0, startPosition.getY()),
            startPosition);
        this.hitbox = new HitboxImpl(SQUAREHITBOX / 2, SQUAREHITBOX, startPosition);
        return new Rocket(this.startPosition, this.rocketVelocity, this.hitbox);
    }

    @Override
    public final LaserRay createLaserRay(Set<Pair<String, GameObject>> entities){
        int y = this.getY(entities);
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND / 2, y);
        this.finishPosition = startPosition;
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl(30, XBOUND, startPosition);
        return new LaserRay(this.startPosition, this.velocity, this.hitbox);
    }

    @Override
    public final ShieldPowerUpImpl createShieldPowerUp(Set<Pair<String, GameObject>> entities) {
        final int duration = random.nextInt(GameFactoryImpl.RANDOMSEED);
        int y = this.getY(entities);
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
    public final SpeedUpPowerUpImpl createSpeedUpPowerUpImpl(Set<Pair<String, GameObject>> entities) {
        final int distance = random.nextInt(GameFactoryImpl.XBOUND);
        int y = this.getY(entities);
        this.startPosition = new Point2d(GameFactoryImpl.XBOUND, y);
        this.finishPosition = new Point2d(GameFactoryImpl.LIMIT, startPosition.getY());
        this.velocity = new Vector2d(finishPosition, startPosition);
        this.hitbox = new HitboxImpl(SQUAREHITBOX, SQUAREHITBOX, startPosition);
        return new SpeedUpPowerUpImpl(distance, this.startPosition, this.velocity, this.hitbox);
    }

    @Override
    public final ScientistImpl createScientist(Set<Pair<String, GameObject>> entities) {
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
    public final GameObject createGenericGameObject(Set<Pair<String, GameObject>> entities) {
        int y = this.getY(entities);
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
     * @return true if y is like someone's else, false otherwise
     */
    private boolean checkY(final int y, Set<Pair<String, GameObject>> entities) {
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
     * @return the y coordinate
     */
    private int getY(Set<Pair<String, GameObject>> entities){
        int y = random.nextInt(GameFactoryImpl.YBOUND);
        while (checkY(y, entities)) {
            y = random.nextInt(GameFactoryImpl.YBOUND);
        }
        return y;
    }
}
