package it.unibo.jetpackjoyride.core.api;

/**
 * Class to do a thread agent to update and reset a value.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public interface Slider {

    /**
     * Method to update the position of the slider.
     */
    void updatePos();

    /**
     * Method to reset the position of the slider.
     */
    void resetPos();

    /**
     * Method to get the position.
     * 
     * @return the position
     */
    int getPos();

}