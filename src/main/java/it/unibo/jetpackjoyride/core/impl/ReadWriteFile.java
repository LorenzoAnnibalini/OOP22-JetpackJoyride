package it.unibo.jetpackjoyride.core.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A simple class to read and write different tipe of files.
 * ONLY WITH SIMPLE TYPES (STRING,INTEGER,DOUBLE,BOOLEAN,ECC)
 * NO OBJECTS
 * @author lorenzo.annibalini@studio.unibo.it
 * @param <T>
 * @param <G>
 */

public class ReadWriteFile<T, G> {

    private String path;

   public ReadWriteFile(final String path) {
        //System.out.println(path);
        this.path = getClass().getClassLoader().getResource(path).getPath();
        //System.out.println(this.path);
    }

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
     * @throws IOException
     */
    public void writeMap(final Map<T,G> map) throws IOException{
        PrintWriter writer = new PrintWriter(new File(this.path));
        for (T name : map.keySet()) {
            writer.write(name + ";" + map.get(name) + "\n");
        }
        writer.close();   
    }

    /**
     * @return the file read as a map
     * @throws FileNotFoundException
     */
    public <T, G> Map<T, G> readMap() throws FileNotFoundException{
        Map<T, G> mappaTmp = new HashMap<T, G>();
        Scanner sc = new Scanner(new File(this.path));
        sc.useDelimiter(";"); 
            while (sc.hasNext()) { 
                    T key = (T)sc.next();
                    G value = (G)sc.next(); 
                    mappaTmp.put(key, value);sc.nextLine();
                    System.out.println("Reading Game Settings : " + key + " " + value);
            }
        sc.close();  
        return mappaTmp;
    }

    public void writeArrayList(final ArrayList<T> list) throws IOException{
        PrintWriter writer = new PrintWriter(new File(this.path));
        for (T name : list) {
            writer.write(name + "\n");
        }
        writer.close();   
    }

    public <T> ArrayList<T> readArrayList() throws FileNotFoundException{
        Scanner file = new Scanner(new File(this.path));
        ArrayList<T> list = new ArrayList<T>();
        while (file.hasNextLine()) {
            String line = file.nextLine();
            list.add((T) line);
        }
        return list;
    }


    
}
