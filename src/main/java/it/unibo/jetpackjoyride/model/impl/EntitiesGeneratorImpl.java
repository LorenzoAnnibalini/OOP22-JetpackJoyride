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
public class EntitiesGeneratorImpl implements EntitiesGenerator {

    private Set<Pair<String, GameObject>> entities = new HashSet<>();
    private static final int ROCKET = 0;
    private static final int ELECTRODE = 1;
    private static final int SHIELDPOWERUP = 2;
    private static final int SPEEDUPPOWERUP = 3;
    private static final int NOTHING = 4;
    private static final int ENTITIESSEED = 5;
    private static final int DURATION = 0;
    private static final long SHORTDURATION = 5000;
    private static final long LONGDURATION = 8000;
    private static final int YBOUND = 500;
    private static final int XBOUND = 1180;
    private static final int LIMIT = EntitiesGeneratorImpl.XBOUND - 395;
    private static final int HORIZONTAL = 0;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int RANDOMSEED = 2;

    @Override
    public void generateEntity(final Set<Pair<String, GameObject>> entities, int num) {
        // Overwrite entities
        this.entities = entities;
        for (int i = 0; i < num; i++) {
            // Variable used to generate random number
            int entityNum = 0;
            Random random = new Random();
            entityNum = random.nextInt(EntitiesGeneratorImpl.ENTITIESSEED);
            System.out.println(entityNum);

            // Vairables for gameobject's parameters constructor
            // Check if the new entity has y like others that are already spawned
            int y = random.nextInt(EntitiesGeneratorImpl.YBOUND);
            while (checkY(y)) {
                y = random.nextInt(EntitiesGeneratorImpl.YBOUND);
            }
            Point2d startPosition = new Point2d(EntitiesGeneratorImpl.XBOUND, y);
            Point2d finishPosition = new Point2d((EntitiesGeneratorImpl.LIMIT), startPosition.y);
            Vector2d velocity = new Vector2d(finishPosition, startPosition);
            Vector2d rocketVelocity = new Vector2d(new Point2d(EntitiesGeneratorImpl.XBOUND, random.nextInt(EntitiesGeneratorImpl.YBOUND)),
                    startPosition);
            HitboxImpl hitbox = new HitboxImpl(46, 46, startPosition);
            // Switch on types of entities based on random result
            switch (entityNum) {
                case EntitiesGeneratorImpl.ROCKET:
                    entities.add(
                            new Pair<String, GameObject>("Rocket",
                                    new Rocket(startPosition, rocketVelocity, hitbox)));
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
                    entities.add(new Pair<String, GameObject>("SpeedUpPowerup",
                            new SpeedUpPowerUpImpl(distance, startPosition, velocity, hitbox)));
                    break;

                case EntitiesGeneratorImpl.NOTHING:
                    entities.add(
                            new Pair<String, GameObject>("Nothing",
                                    new GameObject(startPosition, velocity, new HitboxImpl(0, 0, startPosition))));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void generateScientists(final Set<Pair<String, GameObject>> entities, int num) {
        // Overwrite entities
        this.entities = entities;
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            int direction = random.nextInt(2);
            Point2d startPosition = new Point2d(
                    direction == EntitiesGeneratorImpl.LEFT ? EntitiesGeneratorImpl.XBOUND : 0,
                    EntitiesGeneratorImpl.YBOUND);
            Point2d finishPosition = new Point2d(
                    direction == EntitiesGeneratorImpl.RIGHT ? EntitiesGeneratorImpl.XBOUND : 0,
                    EntitiesGeneratorImpl.YBOUND);
            Vector2d velocity = new Vector2d(finishPosition, startPosition);
            this.entities.add(new Pair<String, GameObject>("Scientist", new ScientistImpl(
                    direction == EntitiesGeneratorImpl.LEFT ? Direction.LEFT : Direction.RIGHT,
                    startPosition,
                    velocity, new HitboxImpl(50, 50, startPosition))));
        }
    }

    @Override
    public void generateLaser(final Set<Pair<String, GameObject>> entities, int num) {
        // Overwrite entities
        this.entities = entities;
        Random random = new Random();
        this.entities.add(new Pair<String, GameObject>("Laser", new LaserRay(
                new Point2d(EntitiesGeneratorImpl.XBOUND, random.nextInt(EntitiesGeneratorImpl.YBOUND)), null,
                null)));
    }

    @Override
    public Set<Pair<String, GameObject>> getEntities() {
        return this.entities;
    }

    @Override
    public void entitiesGarbage(Set<Pair<String, GameObject>> entities) {
        Iterator<Pair<String, GameObject>> iterator = this.entities.iterator();
        while (iterator.hasNext()) {
            Pair<String, GameObject> pair = iterator.next();
            if (pair.getY().getCurrentPos().x < 0
                    || pair.getX() == "Scientist" && pair.getY().getCurrentPos().x > EntitiesGeneratorImpl.XBOUND) {
                iterator.remove();
            }
        }
    }

    /**
     * Method to check if new entities has y like others already spawned
     * 
     * @param y the y value of new entitiy
     * @return true if y is like someone's else, false otherwise
     */
    private boolean checkY(int y) {
        return this.entities.stream()
                .filter(x -> x.getY().getCurrentPos().y - y > -30 && x.getY().getCurrentPos().y - y < 30).count() != 0;
    }
}
