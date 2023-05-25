package it.unibo.jetpackjoyride.core.api;

import java.util.Set;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.impl.Electrode;
import it.unibo.jetpackjoyride.model.impl.GameObject;
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
    
    public Electrode createElectrode(Set<Pair<String, GameObject>> entities);

    public Rocket createRocket(Set<Pair<String, GameObject>> entities);

    public LaserRay createLaserRay(Set<Pair<String, GameObject>> entities);

    public ShieldPowerUpImpl createShieldPowerUp(Set<Pair<String, GameObject>> entities);

    public SpeedUpPowerUpImpl createSpeedUpPowerUpImpl(Set<Pair<String, GameObject>> entities);

    public ScientistImpl createScientist(Set<Pair<String, GameObject>> entities);

    public GameObject createGenericGameObject(Set<Pair<String, GameObject>> entities);
}
