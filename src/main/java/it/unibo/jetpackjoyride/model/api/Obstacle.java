package it.unibo.jetpackjoyride.model.api;

public interface Obstacle {

    /**
     * Set the obstacle active = true.
     */
    void setActiveOn();

    /**
     * Set the obstacle active = false.
     */
    void setActiveOff();

    /**
     * Return true if the obstacle is active, false otherwise.
     * 
     * @return true if obstacle is active, false otherwise
     */
    boolean isActive();
}
