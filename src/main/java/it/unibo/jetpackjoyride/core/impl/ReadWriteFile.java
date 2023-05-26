package it.unibo.jetpackjoyride.core.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A simple class to read and write different tipe of files.
 * ONLY WITH SIMPLE TYPES (STRING,INTEGER,DOUBLE,BOOLEAN,ECC)
 * NO OBJECTS.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 * @param <T> type of the key or type of the list
 * @param <G> type of the value only for the map method
 */

public class ReadWriteFile<T, G> {

    private String path;

    /**
     * Constructor of the class.
     * 
     * @param path
     */
    public ReadWriteFile(final String path) {
        this.path = getClass().getClassLoader().getResource(path).getPath();
    }

    /**
     * @param path the path of the file to read or write
     */
    public void setPath(final String path) {
        this.path = path;
    }

    /**
     * @return the path of the file to read or write
     */
    public String getPath() {
        return path;
    }

    /**
     * Write a map in a file.
     * 
     * @param map the map to write in the file (key;value)
     * only use symple types (String,Integer,Double,Boolean,ECC)
     * @throws IOException
     */
    public void writeMap(final Map<T, G> map) throws IOException {
        final PrintWriter writer = new PrintWriter(new File(this.path));
        for (final T name : map.keySet()) {
            writer.write(name + ";" + map.get(name) + ";" + "\n");
        }
        writer.close();
    }

    /**
     * Read a map from a file.
     * 
     * @return the file read as a map
     * @throws FileNotFoundException
     * @param <T> type of the key
     * @param <G> type of the value
     */
    public <T, G> Map<T, G> readMap() throws FileNotFoundException {
        final Map<T, G> mappaTmp = new HashMap<T, G>();
        final Scanner sc = new Scanner(new File(this.path));
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            final T key = (T) sc.next();
            final G value = (G) sc.next();
            mappaTmp.put(key, value);
            sc.nextLine();
            System.out.println("Reading Game Settings : " + key + " " + value);
        }
        sc.close();
        return mappaTmp;
    }

    /**
     * Write a ArrayList in a file.
     * 
     * @param list the list to write in the file
     * only use symple types (String,Integer,Double,Boolean,ECC)
     * @throws IOException
     */
    public void writeArrayList(final ArrayList<T> list) throws IOException {
        final PrintWriter writer = new PrintWriter(new File(this.path));
        for (final T name : list) {
            writer.write(name + "\n");
        }
        writer.close();
    }


    /**
     * Read a ArrayList from a file.
     * 
     * @return the file read as a ArrayList
     * @throws FileNotFoundException
     * @param <T> type of the list
     */
    public <T> ArrayList<T> readArrayList() throws FileNotFoundException {
        final Scanner file = new Scanner(new File(this.path));
        final ArrayList<T> list = new ArrayList<T>();
        while (file.hasNextLine()) {
            final String line = file.nextLine();
            list.add((T) line);
        }
        return list;
    }

}
