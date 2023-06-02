package it.unibo.jetpackjoyride.model.api;

import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.impl.GameObject;

/**
 * An interface to generate new enetitites in the game map.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public interface EntitiesGenerator {
    /**
     * Method to generate new obstacles (Electrode, LaserRay or Rocket).
     * 
     * @param entities the set of entities already spwaned in game
     * @param num      number of entities to generate
     */
    void generateObstacles(Set<Pair<String, GameObject>> entities, int num);

    /**
     * Method to generate new powerups (Electrode or Rocket).
     * 
     * @param entities the set of entities already spwaned in game
     * @param num      number of entities to generate
     */
    void generatePowerUps(Set<Pair<String, GameObject>> entities, int num);

    /**
     * Method to generate new scientists (5 scientist per time).
     * 
     * @param entities the set of entities already spwaned in game
     * @param num      of scientist to generate
     */
    void generateScientists(Set<Pair<String, GameObject>> entities, int num);

    /**
     * Method to generate a laser.
     * 
     * @param entities the set of entities already spwaned in game
     * @param num      number of laser to generate
     */
    void generateLaser(Set<Pair<String, GameObject>> entities, int num);

    /**
     * Method to get all entities to spawn.
     * 
     * @return a set of gameobjects like electrodes, rockets, laser, powerups, ecc
     */
    Set<Pair<String, GameObject>> getEntities();

}
