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
     * Method to generate a new obstacle (Electrode, LaserRay or Rocket).
     * 
     * @param entities the set of entities already spwaned in game
     * @num number of entities to generate
     */
    void generateEntity(Set<Pair<String, GameObject>> entities, int num);

    /**
     * Method to generate new scientists (5 scientist per time).
     * @param entities the set of entities already spwaned in game
     * @param number of scientist to generate
     */
    void generateScientists(Set<Pair<String, GameObject>> entities, int num);

    /**
     * Method to generate a laser
     * @param entities the set of entities already spwaned in game
     * @param num number of laser to generate
     */
    void generateLaser(Set<Pair<String, GameObject>> entities, int num);

    /**
     * Method to get all entities to spawn.
     * 
     * @return a set of gameobjects like electrodes, rockets, laser, powerups, ecc
     */
    Set<Pair<String, GameObject>> getEntities();

    /**
     * Method to check if an entity is out of visible range and so has to be
     * deleted.
     * 
     * @param entities the set of entities already in game
     */
    void entitiesGarbage(Set<Pair<String, GameObject>> entities);
}
