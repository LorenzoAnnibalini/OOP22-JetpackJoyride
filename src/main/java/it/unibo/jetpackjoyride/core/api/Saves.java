package it.unibo.jetpackjoyride.core.api;

import java.io.FileNotFoundException;
import java.util.List;
/**
 * This is the interface to download and upload datas from file
 * @author emanuele.sanchi@studio.unibo.it
 * 
 */
public interface Saves {

    /**
     * Method to read the file of saves and create the object saves
     * @return list of values from the file
     * @throws FileNotFoundException
     */
    public List<Integer> downloadSaves() throws FileNotFoundException;

    /**
     * Method to upload datas on the file (should be called on game's close)
     */
    public void uploadSaves();
}
