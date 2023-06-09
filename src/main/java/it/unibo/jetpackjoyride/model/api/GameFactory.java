package it.unibo.jetpackjoyride.model.api;

import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;

/**
 * Interface to create the game objects.
 * 
 */
public interface GameFactory {

    /**
     * Method to create an Electrode.
     * @param entities
     * @return the new Electrode
     */
    GameObject createElectrode(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a Rocket.
     * @param entities
     * @return the new Rocket
     */
    GameObject createRocket(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a LaserRay.
     * @param entities
     * @return the new LaserRay
     */
    GameObject createLaserRay(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a ShieldPowerUp.
     * @param entities
     * @return the new ShieldPowerUp
     */
    GameObject createShieldPowerUp(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a SpeedUpPowerUp.
     * @param entities
     * @return the new SpeedUpPowerUp
     */
    GameObject createSpeedUpPowerUpImpl(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a Scientist.
     * @param entities
     * @return the new Scientist
     */
    GameObject createScientist(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a generic GameObject.
     * @param entities
     * @return the new GameObject
     */
    GameObject createGenericGameObject(Set<Pair<String, GameObject>> entities);
}
