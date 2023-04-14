package it.unibo.jetpackjoyride.core.api;

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
     * Method to generate a new power up.
     */
    void powerUp();

    /**
     * Method to generate new scientist.
     */
    void generateScientist();
}
