package it.unibo.jetpackjoyride.model.api;

public interface PowerUp {
    /**
     * Generic PowerUp interface
     * @author: lorenzo.annibalini@studio.unibo.it
     */

    public static enum PowerUpType { SHIELD, SPEED, JETPACK, LIFE }

    /**
     * active the powerup
     */
    public void active();

    /**
     * disable the powerup
     */
    public void disable();

    /**
     * @return true if the powerup is active
     */
    public boolean isActive();

    /**
     * @return the type of the powerup
     */
    public PowerUpType getType();

    /**
     * set the cost in coin of the powerup
     * @param cost the cost in coin of the powerup
     * @throws IllegalArgumentException if cost is negative
     */
    public void setCost(int cost);

    /**
     * @return the cost in coin of the powerup
     */
    public int getCost();

    /**
     * Set the duration of the powerup
     * @param duration the duration of the powerup
     * @throws IllegalArgumentException if duration is negative
     */
    public void setDuration(int duration);

    /**
     * @return the duration of the powerup
     */
    public int getDuration();
}
