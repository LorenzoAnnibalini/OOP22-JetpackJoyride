package it.unibo.jetpackjoyride.model.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import java.util.Iterator;

import it.unibo.jetpackjoyride.core.api.GameFactory;
import it.unibo.jetpackjoyride.core.impl.GameFactoryImpl;
import it.unibo.jetpackjoyride.model.api.EntitiesGenerator;
import it.unibo.jetpackjoyride.common.Pair;

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
    private static final int NOTHING = 2;
    private static final int ENTITIESSEED = 3;
    private static final int XBOUND = 1180;
    private final GameFactory factory = new GameFactoryImpl();

    @Override
    public void generateObstacles(final Set<Pair<String, GameObject>> entities, final int num) {
        // Overwrite entities
        this.entities = entities;
        for (int i = 0; i < num; i++) {
            // Variable used to generate random number
            int entityNum;
            final Random random = new Random();
            entityNum = random.nextInt(EntitiesGeneratorImpl.ENTITIESSEED);
            // Switch on types of entities based on random result
            switch (entityNum) {
                case EntitiesGeneratorImpl.ROCKET:
                    this.entities.add(
                            new Pair<String, GameObject>("Rocket",
                                    this.factory.createRocket(
                                            new HashSet<>(this.entities))));
                    break;
                case EntitiesGeneratorImpl.ELECTRODE:
                    this.entities.add(new Pair<String, GameObject>("Electrode",
                            this.factory.createElectrode(
                                    new HashSet<>(this.entities))));
                    break;
                case EntitiesGeneratorImpl.NOTHING:
                    this.entities.add(
                            new Pair<String, GameObject>("Nothing",
                                    this.factory.createGenericGameObject(
                                            new HashSet<>(this.entities))));
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
            int entityNum;
            final Random random = new Random();
            entityNum = random.nextInt(EntitiesGeneratorImpl.ENTITIESSEED);
            // Switch on types of entities based on random result
            switch (entityNum) {
                case EntitiesGeneratorImpl.SHIELDPOWERUP:
                    this.entities.add(new Pair<String, GameObject>("ShieldPowerUp",
                            this.factory.createShieldPowerUp(
                                    new HashSet<>(this.entities))));
                    break;
                case EntitiesGeneratorImpl.SPEEDUPPOWERUP:
                    this.entities.add(new Pair<String, GameObject>("SpeedUpPowerup",
                            this.factory.createSpeedUpPowerUpImpl(
                                    new HashSet<>(this.entities))));
                    break;

                case EntitiesGeneratorImpl.NOTHING:
                    this.entities.add(
                            new Pair<String, GameObject>("Nothing",
                                    this.factory.createGenericGameObject(
                                            new HashSet<>(this.entities))));
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
        for (int i = 0; i < num; i++) {
            this.entities.add(new Pair<String, GameObject>("Scientist",
                    this.factory.createScientist(
                            new HashSet<>(this.entities))));
        }
    }

    @Override
    public void generateLaser(final Set<Pair<String, GameObject>> entities, final int num) {
        // Overwrite entities
        this.entities = entities;
        for (int i = 0; i < num; i++) {
            this.entities.add(new Pair<String, GameObject>("Laser",
                    this.factory.createLaserRay(
                            new HashSet<>(this.entities))));
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
            if (pair.getY().getCurrentPos().getX() < 0
                    || "Scientist".equals(pair.getX())
                            && pair.getY().getCurrentPos().getX() > EntitiesGeneratorImpl.XBOUND) {
                iterator.remove();
            }
        }
    }
}
