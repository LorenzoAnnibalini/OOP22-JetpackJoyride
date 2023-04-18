package it.unibo.jetpackjoyride.core.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.core.api.EntitiesGeneration;
import it.unibo.jetpackjoyride.model.api.PowerUp;
import it.unibo.jetpackjoyride.model.impl.AutomaticPowerUp;
import it.unibo.jetpackjoyride.model.impl.Electrode;
import it.unibo.jetpackjoyride.model.impl.GameObject;
import it.unibo.jetpackjoyride.model.impl.LaserRay;
import it.unibo.jetpackjoyride.model.impl.ManualPowerUp;
import it.unibo.jetpackjoyride.model.impl.Rocket;

public class EntitiesGenerationImpl implements EntitiesGeneration {

    private Set<GameObject> entities = new HashSet<>();
    private boolean spawnLaser = false;
    private static final int ROCKET = 0;
    private static final int ELECTRODE = 1;
    private static final int POWERUP = 2;
    private static final int YBOUND = 600;
    private static final int XBOUND = 600;
    private static final int HORIZONTAL = 0;
    private long startTime = 0;// System.currentTimeMillis();
    // private static final int ROCKET = 0;

    @Override
    public void generateObstacle() {
        if (this.allowNewEntity()) {
            int entityNum = 0;
            Random random = new Random();
            entityNum = random.nextInt(2);
            switch (entityNum) {
                case EntitiesGenerationImpl.ROCKET:
                    entities.add(new Rocket(
                            new Point2d(EntitiesGenerationImpl.XBOUND, random.nextInt(EntitiesGenerationImpl.YBOUND)),
                            null, null));
                    break;
                case EntitiesGenerationImpl.ELECTRODE:
                    int orientation = random.nextInt(2);
                    entities.add(new Electrode(
                            new Point2d(EntitiesGenerationImpl.XBOUND, random.nextInt(EntitiesGenerationImpl.YBOUND)),
                            null, orientation == EntitiesGenerationImpl.HORIZONTAL ? Electrode.Orientation.HORIZONTAL
                                    : Electrode.Orientation.VERTICAL,
                            null));
                    break;
                case EntitiesGenerationImpl.POWERUP:
                    // entities.add(new ManualPowerUp(null, 0, 0));
                    break;
                default:
                    break;
            }
        }
        this.spawnLaser = this.isLaserSpawnTime();
        if (this.spawnLaser) {
            Random random = new Random();
            this.entities.add(new LaserRay(new Point2d(EntitiesGenerationImpl.XBOUND, random.nextInt(EntitiesGenerationImpl.YBOUND)), null, null));
            this.spawnLaser = false;
        }
    }

    /**
     * Method to check if is allow to add new entity to the game.
     * 
     * @return true if there are less then 3 enetities in the game or if is not time
     *         to spawn a laser
     */
    private boolean allowNewEntity() {
        return this.entities.size() <= 3 || !this.spawnLaser;
    }
    
    /**
     * Method to check if is time to spawn a new entity or if is time for a new laser
     * @return
     */
    private boolean isLaserSpawnTime() {
        return System.currentTimeMillis() - this.startTime % 10000 == 0;
    }

    @Override
    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public Set<GameObject> getEntities() {
        return this.entities;
    }
}
