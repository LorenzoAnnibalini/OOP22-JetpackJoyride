package it.unibo.jetpackjoyride.core.impl;

import java.util.Map;

/**
 * A simple class to read and write different tipe of files.
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class ReadWriteFile {

    private String path;
    private Map map;

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
    public void writeMap(final Map map){
        //TODO
    }

    /**
     * @return the file read as a map
     */
    public Map readMap(){
        //TODO
        return null;
    }


    
}
