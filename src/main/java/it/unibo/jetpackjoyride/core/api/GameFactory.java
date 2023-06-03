package it.unibo.jetpackjoyride.core.api;

import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.GameObject;
import it.unibo.jetpackjoyride.model.impl.Electrode;
import it.unibo.jetpackjoyride.model.impl.LaserRay;
import it.unibo.jetpackjoyride.model.impl.Rocket;
import it.unibo.jetpackjoyride.model.impl.ScientistImpl;
import it.unibo.jetpackjoyride.model.impl.ShieldPowerUpImpl;
import it.unibo.jetpackjoyride.model.impl.SpeedUpPowerUpImpl;

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
    Electrode createElectrode(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a Rocket.
     * @param entities
     * @return the new Rocket
     */
    Rocket createRocket(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a LaserRay.
     * @param entities
     * @return the new LaserRay
     */
    LaserRay createLaserRay(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a ShieldPowerUp.
     * @param entities
     * @return the new ShieldPowerUp
     */
    ShieldPowerUpImpl createShieldPowerUp(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a SpeedUpPowerUp.
     * @param entities
     * @return the new SpeedUpPowerUp
     */
    SpeedUpPowerUpImpl createSpeedUpPowerUpImpl(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a Scientist.
     * @param entities
     * @return the new Scientist
     */
    ScientistImpl createScientist(Set<Pair<String, GameObject>> entities);

    /**
     * Method to create a generic GameObject.
     * @param entities
     * @return the new GameObject
     */
    GameObject createGenericGameObject(Set<Pair<String, GameObject>> entities);
}
