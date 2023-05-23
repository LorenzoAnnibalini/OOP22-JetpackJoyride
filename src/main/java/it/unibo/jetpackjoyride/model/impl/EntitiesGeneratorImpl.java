package it.unibo.jetpackjoyride.model.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import java.util.Iterator;

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
public final class EntitiesGeneratorImpl implements EntitiesGenerator {

    private Set<Pair<String, GameObject>> entities = new HashSet<>();
    private static final int ROCKET = 0;
    private static final int ELECTRODE = 1;
    private static final int SHIELDPOWERUP = 0;
    private static final int SPEEDUPPOWERUP = 1;
    private static final int NOTHING = 3;
    private static final int ENTITIESSEED = 3;
    private static final int DURATION = 0;
    private static final long SHORTDURATION = 5000;
    private static final long LONGDURATION = 8000;
    private static final int YBOUND = 530;
    private static final int XBOUND = 1100;
    private static final int LIMIT = EntitiesGeneratorImpl.XBOUND - 395;
    private static final int SCIENTISTIMIT = EntitiesGeneratorImpl.XBOUND - 500;
    private static final int HORIZONTAL = 0;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int RANDOMSEED = 2;
    private static final int SQUAREHITBOX = 50;
    private static final Pair<Integer, Integer> RECTANGLEHITBOX = new Pair<Integer, Integer>(100, 25);
    private static final int SCIENTISTWIDTH = 30;
    private static final int NOTHINGHITBOX = 0;
    private static final int SPAWNRANGE = 30;

    @Override
    public void generateObstacles(final Set<Pair<String, GameObject>> entities, final int num) {
        // Overwrite entities
        this.entities = entities;
        for (int i = 0; i < num; i++) {
            // Variable used to generate random number
            int entityNum = 0;
            final Random random = new Random();
            entityNum = random.nextInt(EntitiesGeneratorImpl.ENTITIESSEED);
            System.out.println(entityNum);
            // Check if the new entity has y like others that are already spawned
            int y = random.nextInt(EntitiesGeneratorImpl.YBOUND);
            while (checkY(y)) {
                y = random.nextInt(EntitiesGeneratorImpl.YBOUND);
            }
            // Vairables for gameobject's parameters constructor
            final Point2d startPosition = new Point2d(EntitiesGeneratorImpl.XBOUND, y);
            final Point2d finishPosition = new Point2d(EntitiesGeneratorImpl.LIMIT, startPosition.y);
            final Vector2d velocity = new Vector2d(finishPosition, startPosition);
            final Vector2d rocketVelocity = new Vector2d(
                    new Point2d(0, startPosition.y),
                    startPosition);
            HitboxImpl hitbox;
            // Switch on types of entities based on random result
            switch (entityNum) {
                case EntitiesGeneratorImpl.ROCKET:
                    hitbox = new HitboxImpl(SQUAREHITBOX / 2, SQUAREHITBOX, startPosition);
                    entities.add(
                            new Pair<String, GameObject>("Rocket",
                                    new Rocket(startPosition, rocketVelocity, hitbox)));
                    break;
                case EntitiesGeneratorImpl.ELECTRODE:
                    final int orientation = random.nextInt(EntitiesGeneratorImpl.RANDOMSEED);
                    if (orientation == EntitiesGeneratorImpl.HORIZONTAL) {
                        hitbox = new HitboxImpl(RECTANGLEHITBOX.getY(), RECTANGLEHITBOX.getX(),
                                new Point2d(startPosition.x, startPosition.y));
                    } else {
                        hitbox = new HitboxImpl(RECTANGLEHITBOX.getX(), RECTANGLEHITBOX.getY(),
                                new Point2d(startPosition.x, startPosition.y));
                    }
                    entities.add(new Pair<String, GameObject>("Electrode",
                            new Electrode(startPosition, velocity,
                                    orientation == EntitiesGeneratorImpl.HORIZONTAL ? Orientation.HORIZONTAL
                                            : Orientation.VERTICAL,
                                    hitbox)));
                    break;
                case EntitiesGeneratorImpl.NOTHING:
                    entities.add(
                            new Pair<String, GameObject>("Nothing",
                                    new GameObject(startPosition, velocity,
                                            new HitboxImpl(NOTHINGHITBOX, NOTHINGHITBOX, startPosition))));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void generatePowerUps(final Set<Pair<String, GameObject>> entities, final int num) {
        // Overwrite entities
        this.entities = entities;
        for (int i = 0; i < num; i++) {
            // Variable used to generate random number
            int entityNum = 0;
            final Random random = new Random();
            entityNum = random.nextInt(EntitiesGeneratorImpl.ENTITIESSEED);
            System.out.println(entityNum);

            // Vairables for gameobject's parameters constructor
            // Check if the new entity has y like others that are already spawned
            int y = random.nextInt(EntitiesGeneratorImpl.YBOUND);
            while (checkY(y)) {
                y = random.nextInt(EntitiesGeneratorImpl.YBOUND);
            }
            final Point2d startPosition = new Point2d(EntitiesGeneratorImpl.XBOUND, y);
            final Point2d finishPosition = new Point2d(EntitiesGeneratorImpl.LIMIT, startPosition.y);
            final Vector2d velocity = new Vector2d(finishPosition, startPosition);
            final HitboxImpl hitbox = new HitboxImpl(SQUAREHITBOX, SQUAREHITBOX, startPosition);
            // Switch on types of entities based on random result
            switch (entityNum) {
                case EntitiesGeneratorImpl.SHIELDPOWERUP:
                    final int duration = random.nextInt(EntitiesGeneratorImpl.RANDOMSEED);
                    entities.add(new Pair<String, GameObject>("ShieldPowerUp",
                            new ShieldPowerUpImpl(
                                    duration == EntitiesGeneratorImpl.DURATION ? EntitiesGeneratorImpl.SHORTDURATION
                                            : EntitiesGeneratorImpl.LONGDURATION,
                                    startPosition, velocity, hitbox)));
                    break;
                case EntitiesGeneratorImpl.SPEEDUPPOWERUP:
                    final int distance = random.nextInt(EntitiesGeneratorImpl.XBOUND);
                    entities.add(new Pair<String, GameObject>("SpeedUpPowerup",
                            new SpeedUpPowerUpImpl(distance, startPosition, velocity, hitbox)));
                    break;

                case EntitiesGeneratorImpl.NOTHING:
                    entities.add(
                            new Pair<String, GameObject>("Nothing",
                                    new GameObject(startPosition, velocity,
                                            new HitboxImpl(NOTHINGHITBOX, NOTHINGHITBOX, startPosition))));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void generateScientists(final Set<Pair<String, GameObject>> entities, final int num) {
        // Overwrite entities
        this.entities = entities;
        final Random random = new Random();
        for (int i = 0; i < num; i++) {
            final int direction = random.nextInt(2);
            final Point2d startPosition = new Point2d(
                    direction == EntitiesGeneratorImpl.LEFT ? EntitiesGeneratorImpl.XBOUND : 0,
                    EntitiesGeneratorImpl.YBOUND);
            final Point2d finishPosition = new Point2d(
                    direction == EntitiesGeneratorImpl.RIGHT ? EntitiesGeneratorImpl.SCIENTISTIMIT
                            : EntitiesGeneratorImpl.XBOUND - EntitiesGeneratorImpl.SCIENTISTIMIT,
                    EntitiesGeneratorImpl.YBOUND);
            final Vector2d velocity = new Vector2d(finishPosition, startPosition);
            this.entities.add(new Pair<String, GameObject>("Scientist", new ScientistImpl(
                    direction == EntitiesGeneratorImpl.LEFT ? Direction.LEFT : Direction.RIGHT,
                    startPosition,
                    velocity, new HitboxImpl(SQUAREHITBOX, SCIENTISTWIDTH, startPosition))));
        }
    }

    @Override
    public void generateLaser(final Set<Pair<String, GameObject>> entities, final int num) {
        // Overwrite entities
        this.entities = entities;
        for (int i = 0; i < num; i++) {
            final Random random = new Random();
            final Point2d startPosition = new Point2d(EntitiesGeneratorImpl.XBOUND / 2,
                    random.nextInt(EntitiesGeneratorImpl.YBOUND));
            final Point2d finishPosition = startPosition;
            final Vector2d velocity = new Vector2d(finishPosition, startPosition);
            final HitboxImpl hitbox = new HitboxImpl(30, XBOUND, startPosition);
            this.entities.add(new Pair<String, GameObject>("Laser", new LaserRay(startPosition, velocity, hitbox)));
        }

    }

    @Override
    public Set<Pair<String, GameObject>> getEntities() {
        return this.entities;
    }

    @Override
    public void entitiesGarbage(final Set<Pair<String, GameObject>> entities) {
        final Iterator<Pair<String, GameObject>> iterator = this.entities.iterator();
        while (iterator.hasNext()) {
            final Pair<String, GameObject> pair = iterator.next();
            if (pair.getY().getCurrentPos().x < 0
                    || pair.getX() == "Scientist" && pair.getY().getCurrentPos().x > EntitiesGeneratorImpl.XBOUND) {
                iterator.remove();
            }
        }
    }

    /**
     * Method to check if new entities has y like others already spawned.
     * 
     * @param y the y value of new entitiy
     * @return true if y is like someone's else, false otherwise
     */
    private boolean checkY(final int y) {
        return this.entities.stream()
                .filter(x -> x.getY().getCurrentPos().y - y > -SPAWNRANGE
                        && x.getY().getCurrentPos().y - y < SPAWNRANGE)
                .count() != 0;
    }
}
