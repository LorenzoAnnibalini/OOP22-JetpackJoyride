package it.unibo.jetpackjoyride.model.api;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;

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
     * Method to get the current position of the scientist
     * @return the current position of the scientist
     */
    public Point2d getCurrentPos();

    /**
     * Method to set the current position of the scientist
     * @param point the new position of the scientist
     * @throws IllegalArgumentException if the point is null
     */
    public void setCurrentPos(Point2d point);

    /**
     * Method to get the current velocity of the scientist
     * @return the current velocity of the scientist
     */
    public Vector2d getVelocity();

    /**
     * Method to set the current velocity of the scientist
     * @param velocity the new velocity of the scientist
     * @throws IllegalArgumentException if the velocity is null
     */
    public void setVelocity(Vector2d velocity);

    //TODO: vedi commento a inizio interfaccia
    /**
     * Method to get the texture array of the scientist
     * @return the texture array of the scientist
     */
    public String[] getTextureArray();

    /**
     * Method to get the current texture of the scientist
     * @return the current texture of the scientist
     */
    public String getCurrentTexture();

    //TODO: vedi commento a inizio interfaccia
    /**
     * Method to set the array of textures of the scientist
     * @param texture the array of textures
     * @throws IllegalArgumentException if the textures array is empty
     */
    public void setTextureArray(String[] texture);

    //TODO: vedi commento a inizio interfaccia
    /**
     * Method to simulate the scientist's run
     * Increment the texture index 
     * Update the position of the scientist according to the direction
     * @return the current texture of the scientist
     */
    public String run();
    
}
