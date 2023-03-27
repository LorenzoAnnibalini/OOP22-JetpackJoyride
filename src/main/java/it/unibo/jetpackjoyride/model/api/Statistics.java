package it.unibo.jetpackjoyride.model.api;

/**
 * This is the class that updates the statistic of the game
 * @author emanuele.sanchi@studio.unibo.it
 * 
 */
public interface Statistics {
    /**
     * Method that updates the deaths of the player
     */
    public int updateDeaths();

    /**
     * Method that updates the counter of killed npc
     */
    public int updateKilledNpc();

    /**
     * Method that updates the counter of money spent in the shop
     */
    public int moneySpent();

    /**
     * Method that update the record for max reahced meters 
     */
    public int maxMeters();

    /**
     * Method that update total meters performed
     */
    public int totalMeters();

    
}
