package it.unibo.jetpackjoyride.model.api;

import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.impl.GameObject;

/**
 * An interface to generate new enetitites in the game map.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public interface EntitiesGeneration {
    /**
     * Method to generate a new obstacle (Electrode, LaserRay or Rocket).
     */
    void generateObstacle();

    /**
     * Method to generate new scientists (5 scientist per time).
     */
    void generateScientists();

    /**
     * Method to get all entities to spawn.
     * 
     * @return a set of gameobjects like electrodes, rockets, laser, powerups, ecc
     */
    Set<Pair<String, GameObject>> getEntities();
}
