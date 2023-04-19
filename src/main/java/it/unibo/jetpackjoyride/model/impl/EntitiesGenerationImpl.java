package it.unibo.jetpackjoyride.model.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.api.EntitiesGeneration;
import it.unibo.jetpackjoyride.model.api.PowerUp;
import it.unibo.jetpackjoyride.common.Pair;

/**
 * Implementation of class EntitiesGeneration. This class create an object to
 * spawn the entities in game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class EntitiesGenerationImpl implements EntitiesGeneration {

    private Set<Pair<String, GameObject>> entities = new HashSet<>();
    private boolean spawnLaser = false;
    private static final int ROCKET = 0;
    private static final int ELECTRODE = 1;
    private static final int POWERUP = 2;
    private static final int YBOUND = 600;
    private static final int XBOUND = 600;
    private static final int HORIZONTAL = 0;
    private static final int LEFT = 0;
    private static final int SCINETISTS = 5;
    private static final int SHIELD = 0;
    private static final int RANDOMSEED = 2;
    private static final int MAXENTITIES = 4;
    private static final long LASERTIME = 10000; // 10 seconds
    private long startTime = 0;

    public EntitiesGenerationImpl() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void generateObstacle() {
        if (this.allowNewEntity()) {
            int entityNum = 0;
            Random random = new Random();
            entityNum = random.nextInt(3);
            switch (entityNum) {
                case EntitiesGenerationImpl.ROCKET:
                    entities.add(new Pair<String, GameObject>("Rocket", new Rocket(
                            new Point2d(EntitiesGenerationImpl.XBOUND, random.nextInt(EntitiesGenerationImpl.YBOUND)),
                            new Vector2d(0, random.nextDouble(EntitiesGenerationImpl.YBOUND)),
                            new HitboxImpl(50, 50, new Point2d(EntitiesGenerationImpl.XBOUND,
                                    random.nextInt(EntitiesGenerationImpl.YBOUND))))));
                    break;
                case EntitiesGenerationImpl.ELECTRODE:
                    int orientation = random.nextInt(EntitiesGenerationImpl.RANDOMSEED);
                    entities.add(new Pair<String, GameObject>("Electorde", new Electrode(
                            new Point2d(EntitiesGenerationImpl.XBOUND, random.nextInt(EntitiesGenerationImpl.YBOUND)),
                            new Vector2d(0, random.nextDouble(EntitiesGenerationImpl.YBOUND)),
                            orientation == EntitiesGenerationImpl.HORIZONTAL ? Electrode.Orientation.HORIZONTAL
                                    : Electrode.Orientation.VERTICAL,
                            new HitboxImpl(50, 50, new Point2d(EntitiesGenerationImpl.XBOUND,
                                    random.nextInt(EntitiesGenerationImpl.YBOUND))))));
                    break;
                case EntitiesGenerationImpl.POWERUP:
                    int type = random.nextInt(EntitiesGenerationImpl.RANDOMSEED);
                    entities.add(new Pair<String, GameObject>("Powerup",
                            new ManualPowerUp(type == EntitiesGenerationImpl.SHIELD ? PowerUp.PowerUpType.SHIELD
                                    : PowerUp.PowerUpType.SPEED, 500, 5000)));
                    break;
                default:
                    break;
            }
        }
        this.spawnLaser = this.isLaserSpawnTime();
        if (this.spawnLaser) {
            Random random = new Random();
            this.entities.add(new Pair<String, GameObject>("Laser", new LaserRay(
                    new Point2d(EntitiesGenerationImpl.XBOUND, random.nextInt(EntitiesGenerationImpl.YBOUND)), null,
                    null)));
            this.spawnLaser = false;
        }
    }

    @Override
    public void generateScientists() {
        Random random = new Random();
        for (int i = 0; i < EntitiesGenerationImpl.SCINETISTS; i++) {
            int direction = random.nextInt(2);
            this.entities.add(new Pair<String, GameObject>("Scientist", new ScientistImpl(
                    direction == EntitiesGenerationImpl.LEFT ? Direction.LEFT : Direction.RIGHT,
                    new Point2d(direction == EntitiesGenerationImpl.LEFT ? EntitiesGenerationImpl.XBOUND : 0,
                            EntitiesGenerationImpl.YBOUND),
                    null, null)));
        }
    }

    /**
     * Method to check if is allow to add new entity to the game.
     * 
     * @return true if there are less then 3 enetities in the game (scientists are not count) or if is not time
     *         to spawn a laser
     */
    private boolean allowNewEntity() {
        return this.entities.size() - this.entities.stream().filter(x -> x.getX() == "Scientist")
                .count() <= EntitiesGenerationImpl.MAXENTITIES || !this.spawnLaser;
    }

    /**
     * Method to check if is time to spawn a new entity or if is time for a new
     * laser
     * 
     * @return true if are passed 10 seconds from last laser
     */
    private boolean isLaserSpawnTime() {
        return System.currentTimeMillis() - this.startTime % EntitiesGenerationImpl.LASERTIME == 0;
    }

    @Override
    public Set<Pair<String, GameObject>> getEntities() {
        return this.entities;
    }

}
