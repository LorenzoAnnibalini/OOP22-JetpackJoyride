package it.unibo.jetpackjoyride.model.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.api.EntitiesGenerator;
import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.Orientation;

/**
 * Implementation of class EntitiesGeneration. This class create an object to
 * spawn the entities in game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class EntitiesGeneratorImpl implements EntitiesGenerator {

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
    private static final int SCIENTISTS = 5;
    private static final int RANDOMSEED = 2;
    private static final int MAXENTITIES = 4;
    private static final long LASERTIME = 10000; // 10 seconds
    private long startTime = 0;

    public EntitiesGeneratorImpl() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void generateEntity(Set<Pair<String, GameObject>> entities) {
        if (this.allowNewEntity()) {
            // Overwrite entities
            this.entities = entities; // forse andrà spostato sopra al controllo if
            // Variable used to generate random number
            int entityNum = 0;
            Random random = new Random();
            entityNum = random.nextInt(EntitiesGeneratorImpl.ENTITIESSEED);
            System.out.println(entityNum);
            // Vairables for gameobject's parameters constructor
            // Variable to check if the new enetity has a different x than others entities
            boolean checkY = true;
            while (checkY) {
                Point2d startPosition = new Point2d(EntitiesGeneratorImpl.XBOUND,
                        random.nextInt(EntitiesGeneratorImpl.YBOUND));
                for (Pair<String, GameObject> el : this.entities) {
                    if (el.getY().getCurrentPos().y == startPosition.y) {
                        checkY = false;
                        break;
                    } else {
                        checkY = true;
                    }
                }
            }
            Point2d startPosition = new Point2d(EntitiesGeneratorImpl.XBOUND,
                    random.nextInt(EntitiesGeneratorImpl.YBOUND));
            Point2d finishPosition = new Point2d(0, startPosition.y);
            Vector2d velocity = new Vector2d(startPosition, finishPosition);
            Vector2d rocketVelocity = new Vector2d(startPosition,
                    new Point2d(0, random.nextInt(EntitiesGeneratorImpl.YBOUND)));
            HitboxImpl hitbox = new HitboxImpl(50, 50, startPosition);
            // Switch on types of entities based on random result
            switch (entityNum) {
                case EntitiesGeneratorImpl.ROCKET:
                    entities.add(
                            new Pair<String, GameObject>("Rocket", new Rocket(startPosition, rocketVelocity, hitbox)));
                    break;
                case EntitiesGeneratorImpl.ELECTRODE:
                    int orientation = random.nextInt(EntitiesGeneratorImpl.RANDOMSEED);
                    entities.add(new Pair<String, GameObject>("Electrode",
                            new Electrode(startPosition, velocity,
                                    orientation == EntitiesGeneratorImpl.HORIZONTAL ? Orientation.HORIZONTAL
                                            : Orientation.VERTICAL,
                                    hitbox)));
                    break;

                case EntitiesGeneratorImpl.SHIELDPOWERUP:
                    int duration = random.nextInt(EntitiesGeneratorImpl.RANDOMSEED);
                    entities.add(new Pair<String, GameObject>("ShieldPowerUp",
                            new ShieldPowerUpImpl(
                                    duration == EntitiesGeneratorImpl.DURATION ? EntitiesGeneratorImpl.SHORTDURATION
                                            : EntitiesGeneratorImpl.LONGDURATION,
                                    startPosition, velocity, hitbox)));
                    break;
                case EntitiesGeneratorImpl.SPEEDUPPOWERUP:
                    int distance = random.nextInt(EntitiesGeneratorImpl.XBOUND);
                    entities.add(new Pair<String, GameObject>("Powerup",
                            new SpeedUpPowerUpImpl(distance, startPosition, velocity, hitbox)));
                    break;

                case EntitiesGeneratorImpl.NOTHING:
                    entities.add(
                            new Pair<String, GameObject>("Nothing", new GameObject(startPosition, velocity, hitbox)));
                    break;
                default:
                    break;
            }
            this.entitiesGarbage();
        }
        this.spawnLaser = this.isLaserSpawnTime();
        if (this.spawnLaser) {
            Random random = new Random();
            this.entities.add(new Pair<String, GameObject>("Laser", new LaserRay(
                    new Point2d(EntitiesGeneratorImpl.XBOUND, random.nextInt(EntitiesGeneratorImpl.YBOUND)), null,
                    null)));
            this.spawnLaser = false;
        }
    }

    @Override
    public void generateScientists() {
        Random random = new Random();
        for (int i = 0; i < EntitiesGeneratorImpl.SCIENTISTS; i++) {
            int direction = random.nextInt(2);
            this.entities.add(new Pair<String, GameObject>("Scientist", new ScientistImpl(
                    direction == EntitiesGeneratorImpl.LEFT ? Direction.LEFT : Direction.RIGHT,
                    new Point2d(direction == EntitiesGeneratorImpl.LEFT ? EntitiesGeneratorImpl.XBOUND : 0,
                            EntitiesGeneratorImpl.YBOUND),
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
        for (Pair<String, GameObject> pair : this.entities) {
            if (pair.getY().getCurrentPos().x < 0
                    || (pair.getX() == "Scientist" && pair.getY().getCurrentPos().x > EntitiesGeneratorImpl.XBOUND)) {
                this.entities.remove(pair);
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
                .count() <= EntitiesGeneratorImpl.MAXENTITIES || !this.spawnLaser;
    }

    /**
     * Method to check if is time to spawn a new entity or if is time for a new
     * laser
     * 
     * @return true if are passed 10 seconds from last laser
     */
    private boolean isLaserSpawnTime() {
        return System.currentTimeMillis() - this.startTime % EntitiesGeneratorImpl.LASERTIME == 0;
    }
}
