package it.unibo.jetpackjoyride.model.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.api.EntitiesGeneration;
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
    private static final int SHIELDPOWERUP = 2;
    private static final int SPEEDUPPOWERUP = 3;
    private static final int NOTHING = 4;
    private static final int ENTITIESSEED = 5;
    private static final int DURATION = 0;
    private static final long SHORTDURATION = 5000;
    private static final long LONGDURATION = 8000;
    private static final int YBOUND = 600;
    private static final int XBOUND = 600;
    private static final int HORIZONTAL = 0;
    private static final int LEFT = 0;
    private static final int SCINETISTS = 5;
    private static final int RANDOMSEED = 2;
    private static final int MAXENTITIES = 4;
    private static final long LASERTIME = 10000; // 10 seconds
    private long startTime = 0;

    public EntitiesGenerationImpl() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void generateEntity() {
        if (this.allowNewEntity()) {
            int entityNum = 0;
            Random random = new Random();
            entityNum = random.nextInt(EntitiesGenerationImpl.ENTITIESSEED);
            System.out.println(entityNum);
            Point2d startPosition = new Point2d(EntitiesGenerationImpl.XBOUND,
                    random.nextInt(EntitiesGenerationImpl.YBOUND));
            Point2d finishPosition = new Point2d(0, random.nextInt(EntitiesGenerationImpl.YBOUND));
            Vector2d velocity = new Vector2d(startPosition, finishPosition);
            HitboxImpl hitbox = new HitboxImpl(50, 50, startPosition);
            switch (entityNum) {
                case EntitiesGenerationImpl.ROCKET:
                    entities.add(new Pair<String, GameObject>("Rocket", new Rocket(finishPosition, velocity, hitbox)));
                    break;
                case EntitiesGenerationImpl.ELECTRODE:
                    int orientation = random.nextInt(EntitiesGenerationImpl.RANDOMSEED);
                    entities.add(new Pair<String,GameObject>("Electrode", new Electrode(startPosition, velocity, orientation == EntitiesGenerationImpl.HORIZONTAL ? Electrode.Orientation.HORIZONTAL
                    : Electrode.Orientation.VERTICAL, hitbox)));
                    break;
                
                /*case EntitiesGenerationImpl.SHIELDPOWERUP:
                    int duration = random.nextInt(EntitiesGenerationImpl.RANDOMSEED);
                    entities.add(new Pair<String,GameObject>("ShieldPowerUp", new ShieldPowerUp(startPosition, velocity, hitbox, duration)));
                break;
                case EntitiesGenerationImpl.SPEEDUPPOWERUP:
                    int distance = random.nextInt(EntitiesGenerationImpl.XBOUND);
                    entities.add(new Pair<String, GameObject>("Powerup",
                    new SpeedPowerUpImpl(startPosition, velocity, hitbox ,distance)));
                break;*/
                case EntitiesGenerationImpl.NOTHING:
                    entities.add(
                    new Pair<String, GameObject>("Nothing", new GameObject(finishPosition, velocity, hitbox)));
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
                    new Vector2d(new Point2d(i, direction), null), new HitboxImpl(50, 50, null))));
        }
    }

    @Override
    public Set<Pair<String, GameObject>> getEntities() {
        return this.entities;
    }

    /**
     * Method to check if an entity is out of visible range and so has to be
     * deleted.
     */
    private void entitiesGarbage() {
        for (Pair<String, GameObject> pair : entities) {
            if (pair.getY().getCurrentPos().x < 0
                    || (pair.getX() == "Scientist" && pair.getY().getCurrentPos().x > EntitiesGenerationImpl.XBOUND)) {
                entities.remove(pair);
            }
        }
    }

    /**
     * Method to check if is allow to add new entity to the game.
     * 
     * @return true if there are less then 3 enetities in the game (scientists are
     *         not count) or if is not time
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
}
