package it.unibo.jetpackjoyride.model.api;

/**
 * This is the class that updates the statistic of the game
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
    public void setDeaths();

    /**
     * Method to get the amount of killed npc
     * @return the number of killed npc
     */
    public int getKilledNpc();

    /**
     * Method that increments the counter of killed npc
     */
    public void setKilledNpc();

    /**
     * Method to get the amount of grabbed money  during the games
     * @return the number of grabbed money 
     */
    public int getGrabbedMoney();

    /**
     * Method to set the amount of grabbed money of the last game
     */
    public void setGrabbedMoney(int lastGameMoney);

    /**
     * Method to get the amount of money spent in the shop
     * @return the amount of money spent
     */
    public int getMoneySpent();

    /**
     * Method that updates the counter of money spent in the shop
     * @param newPayment
     *          the amount of money spent on the last payment
     */
    public void setMoneySpent(int newPayment);

    /**
     * Method to get the record for max reahced meters 
     * @return the value of maximum meters reached 
     */
    public int getMaxMeters();

    /**
     * Method to set a new record for max meters
     * @param newRecord
     *          the newRecord
     */
    public void setMaxMeters(int newRecord);

    /**
     * Method that get total meters performed
     * @return the value of total meters performed
     */
    public int getTotalMeters();

    /**
     * Method to add meters of the last game
     * @param lastGameMeters
     *          meters of the last game
     */
    public void setTotalMeters(int lastGameMeters);

    /**
     * Method to get the amount of grabbed objects during the games
     * @return the number of grabbed objects
     */
    public int getGrabbedObjects();

    /**
     * Method to increment the number of grabbed objects
     */
    public void setGrabbedObjects();

    
}
