package it.unibo.jetpackjoyride.model.api;

/**
 * TODO: Mettiamo la PATH della texture qui ?
 * Interface for the scientist
 * @author lorenzo.annibalini@studio.unibo.it
 */

public interface Scientist {
    
    /**
     * Method to get the Scientist's direction
     * @return the current direction of the scientist
     */
    public Direction getDirection();

    /**
     * Method to get if the scientist is alive
     * @return true if the scientist is alive, false otherwise
     */
    public Boolean isAlive();

    /**
     * Method to kill the Scientist's
     * Set the life of the scientist to false
     */
    public void killScientist();

    /**
     * Method to set the speed of the scientist
     * @param speed the speed of the scientist
     */
    public void setSpeed(double speed);

    /**
     * Method to get the speed of the scientist
     * @return the speed of the scientist
     * @throws IllegalArgumentException if the speed is not allowed
     */
    public double getSpeed();

    //TODO: vedi commento a inizio interfaccia
    /**
     * Method to get the texture of the scientist
     * @return the texture array of the scientist
     */
    public String[] getTexture();

    //TODO: vedi commento a inizio interfaccia
    /**
     * Method to set the texture of the scientist
     * @param texture the texture of the scientist
     * @throws IllegalArgumentException if the texture array is empty
     */
    public void setTexture(String[] texture);

    /**
     * Method to simulate the scientist's run
     * @return the next texture of the scientist and increment the textureIndex
     */
    public String run();
    
}
