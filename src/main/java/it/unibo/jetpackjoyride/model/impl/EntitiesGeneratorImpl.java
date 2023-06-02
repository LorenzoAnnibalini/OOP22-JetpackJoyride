package it.unibo.jetpackjoyride.model.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

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
    private static final Random RANDOM = new Random();
    private final GameFactory factory = new GameFactoryImpl();
    private static final String SUPPVALUE = "EI_EXPOSE_REP2";
    private static final String SUPPJUSTIFICATION = "Entities are meant to be the same for the generator and the world";

    @Override
    @SuppressFBWarnings(value = SUPPVALUE, justification = SUPPJUSTIFICATION)
    public void generateObstacles(final Set<Pair<String, GameObject>> entities, final int num) {
        // Overwrite entities
        this.entities = entities;
        for (int i = 0; i < num; i++) {
            // Switch on types of entities based on random result
            switch (RANDOM.nextInt(EntitiesGeneratorImpl.ENTITIESSEED)) {
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
    @SuppressFBWarnings(value = SUPPVALUE, justification = SUPPJUSTIFICATION)
    public void generatePowerUps(final Set<Pair<String, GameObject>> entities, final int num) {
        // Overwrite entities
        this.entities = entities;
        for (int i = 0; i < num; i++) {
            // Switch on types of entities based on random result
            switch (RANDOM.nextInt(EntitiesGeneratorImpl.ENTITIESSEED)) {
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
    @SuppressFBWarnings(value = SUPPVALUE, justification = SUPPJUSTIFICATION)
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
    @SuppressFBWarnings(value = SUPPVALUE, justification = SUPPJUSTIFICATION)
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
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = SUPPJUSTIFICATION)
    public Set<Pair<String, GameObject>> getEntities() {
        return this.entities;
    }

}
