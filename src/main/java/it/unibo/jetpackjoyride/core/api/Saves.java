package it.unibo.jetpackjoyride.core.api;

import java.util.Map;

/**
 * Interface for classes to save statistics on file.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public interface Saves {
    /**
     * Method to get the value from file and save them into HashMap in class
     * Statistics.
     * 
     * @return a map of statistics
     */
    Map<String, Integer> downloadSaves();

    /**
     * Method to save newstatistcs in file
     * 
     * @param stats the map to get value that has to be save
     */
    void uploadSaves(Map<String, Integer> stats);
}
