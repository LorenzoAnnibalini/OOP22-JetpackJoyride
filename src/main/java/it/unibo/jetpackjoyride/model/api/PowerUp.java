package it.unibo.jetpackjoyride.model.api;

public interface PowerUp {
    public static enum PowerUpType { SHIELD, SPEED, JETPACK, LIFE }

    /**
     * @return true if the powerup is active
     */
    public boolean isActive();

    /**
     * @return the type of the powerup
     */
    public PowerUpType getType();

    /**
     * @return the cost in coin of the powerup
     */
    public int getCost();


    /**
     * @return the duration of the powerup
     */
    public long getDuration();

    /**
     * @return the start time of the powerup
     */
    public long getStartTime();
}