package it.unibo.jetpackjoyride.core.api;

import java.io.FileNotFoundException;
import java.io.IOException;
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
     * @throws FileNotFoundException if the file is not found
     * @return a map of statistics
     */
    Map<String, Integer> downloadSaves() throws FileNotFoundException;

    /**
     * Method to save newstatistcs in file
     * 
     * @param stats the map to get value that has to be save
     * @throws IOException
     */
    void uploadSaves(Map<String, Integer> stats) throws IOException;
}
