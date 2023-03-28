package it.unibo.jetpackjoyride.model.api;

/**
 * This is the interface to get and set the statistic of the game
 * @author emanuele.sanchi@studio.unibo.it
 * 
 */
public interface Statistics {
    
    /**
     * Method to get the player's deaths
     * @return the number of deaths
     */
    public int getDeaths();

    /**
     * Method that increments the deaths of the player
     */
    public void incrementDeaths();

    /**
     * Method to get the amount of killed npc
     * @return the number of killed npc
     */
    public int getKilledNpc();

    /**
     * Method that increments the counter of killed npc
     */
    public void incrementKilledNpc();

    /**
     * Method to get the amount of grabbed money during the games
     * @return the number of grabbed money 
     */
    public int getGrabbedMoney();

    /**
     * Method to add the amount of grabbed money of the last game to the total
     * @param amount money reahced on last game
     */
    public void setGrabbedMoney(int amount);

    /**
     * Method to get the maximum quantity of money grabbed in one game
     * @return number of maximum money grabbed
     */
    public int getMaxMoney();

    /**
     * Method to get the amount of money spent in the shop
     * @return the amount of money spent
     */
    public int getMoneySpent();

    /**
     * Method that updates the counter of money spent in the shop
     * @param amount the amount of money spent on the last payment
     */
    public void setMoneySpent(int amount);

    /**
     * Method to get the record for max reahced meters 
     * @return the value of maximum meters reached 
     */
    public int getMaxMeters();

    /**
     * Method to get total meters performed
     * @return the value of total meters performed
     */
    public int getTotalMeters();

    /**
     * Method to add meters of the last game
     * @param meters meters of the last game
     */
    public void setTotalMeters(int meters);

    /**
     * Method to get the amount of grabbed objects during the games
     * @return the number of grabbed objects
     */
    public int getGrabbedObjects();

    /**
     * Method to increment the number of grabbed objects
     */
    public void incrementGrabbedObjects();

    
}
