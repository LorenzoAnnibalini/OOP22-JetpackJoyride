package it.unibo.jetpackjoyride.core.impl;

import java.util.ArrayList;
import java.util.Map;

/**
 * A simple class to read and write different tipe of files.
 * @author lorenzo.annibalini@studio.unibo.it
 * @param <T>
 * @param <G>
 */

public class ReadWriteFile<T, G> {

    private String path;
    private Map<T, G> map;
    private ArrayList<T> list;

    /**
     * @param path the path of the file to read or write
     */
   public void setPath (final String path) {
        this.path = path;
    }

    /**
     * @return the path of the file to read or write
     */
    public String getPath() {
        return path;
    }

    /**
     * @param map the map to write in the file
     */
    public void writeMap(final Map<T,G> map){
        //TODO
    }

    /**
     * @return the file read as a map
     */
    public <T, G> Map<T, G> readMap(){
        //TODO
        return null;
    }

    public void writeArrayList(final ArrayList<T> list){
        //TODO
    }

    public <T> ArrayList<T> readArrayList(){
        //TODO
        return null;
    }


    
}
