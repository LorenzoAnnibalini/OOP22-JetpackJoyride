package it.unibo.jetpackjoyride.core.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A simple class to read and write different tipe of files.
 * @author lorenzo.annibalini@studio.unibo.it
 * @param <T>
 * @param <G>
 */

public class ReadWriteFile<T, G> {

    private String path;
    private String name;
    private Map<T, G> map;
    private ArrayList<T> list;

   public ReadWriteFile(final String path, final String name) {
        this.path = this.getClass().getResourceAsStream(path).toString();
        System.out.println(this.path);
        this.name = name;
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
     * @param name the name of the file to read or write
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param name the name of the file to read or write
     */
    public String getName() {
        return name;
    }

    /**
     * @param map the map to write in the file
     * @throws IOException
     */
    public void writeMap(final Map<T,G> map) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.path + this.name));
        for (T key : map.keySet()) {
            writer.write(key + ";" + map.get(key) + "\n");
        }
        writer.close();
        
    }

    /**
     * @return the file read as a map
     * @throws FileNotFoundException
     */
    public <T, G> Map<T, G> readMap() throws FileNotFoundException{
        Map<T, G> map = new HashMap<T, G>();
        Scanner sc = new Scanner(new File(this.path + this.name));  
        sc.useDelimiter(";"); 
            while (sc.hasNext()) { 
                    T key = (T)sc.next();
                    G value = (G)sc.next();  
                    map.put(key, value);
                    System.out.println("Reading Game Settings : " + key + " " + value);
            }
        sc.close();  
        return null;
    }

    public void writeArrayList(final ArrayList<T> list) throws IOException{
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.path + this.name));
            for (T element : list) {
                writer.write(element + "\n");
            }
            writer.close();
    }

    public <T> ArrayList<T> readArrayList() throws FileNotFoundException{
        Scanner file = new Scanner(new File(this.path + this.name));
        ArrayList<T> list = new ArrayList<T>();
        while (file.hasNextLine()) {
            String line = file.nextLine();
            list.add((T) line);
        }
        return list;
    }


    
}
