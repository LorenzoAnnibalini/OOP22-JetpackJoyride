package it.unibo.jetpackjoyride.core.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import it.unibo.jetpackjoyride.model.impl.Deaths;
import it.unibo.jetpackjoyride.model.impl.GrabbedMoney;
import it.unibo.jetpackjoyride.model.impl.GrabbedObjects;
import it.unibo.jetpackjoyride.model.impl.KilledNpc;
import it.unibo.jetpackjoyride.model.impl.MaxMeters;
import it.unibo.jetpackjoyride.model.impl.MaxMoney;
import it.unibo.jetpackjoyride.model.impl.MoneySpent;
import it.unibo.jetpackjoyride.model.impl.TotalMeters;

import java.io.*;

/**
 * Class to save statistics on csv file.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class Saves {

    private final static String SEPARATOR = File.separator;
    private static Deaths deaths;
    private static KilledNpc killedNpc;
    private static GrabbedMoney grabbedMoney;
    private static GrabbedObjects grabbedObjects;
    private static MaxMeters maxMeters;
    private static MaxMoney maxMoney;
    private static MoneySpent moneySpent;
    private static TotalMeters totalMeters;
    private final static String filename = "resources" + SEPARATOR + "saves.csv";

    /**
     * Method to download datas from csv file.
     * 
     * @return a map that maps every stat name with his value
     * @throws FileNotFoundException
     */
    public static Map<String, Integer> downloadSaves() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        Map<String, Integer> statistics = new HashMap<>();
        sc.useDelimiter(";");
        while (sc.hasNextLine()) {
            String name = sc.next();
            int value = sc.nextInt();
            statistics.put(name, value);
        }
        sc.close();
        // Creation of statistics objects
        deaths = new Deaths(statistics.get(statistics.keySet().stream().filter(x -> x == "Deaths").findFirst().get()));
        grabbedMoney = new GrabbedMoney(statistics.get(statistics.keySet().stream().filter(x -> x == "GrabbedMoney").findFirst().get()));
        grabbedObjects = new GrabbedObjects(statistics.get(statistics.keySet().stream().filter(x -> x == "GrabbedObjects").findFirst().get()));
        killedNpc = new KilledNpc(statistics.get(statistics.keySet().stream().filter(x -> x == "KilledNpc").findFirst().get()));
        maxMeters = new MaxMeters(statistics.get(statistics.keySet().stream().filter(x -> x == "MaxMeters").findFirst().get()));
        maxMoney = new MaxMoney(statistics.get(statistics.keySet().stream().filter(x -> x == "MaxMoney").findFirst().get()));
        moneySpent = new MoneySpent(statistics.get(statistics.keySet().stream().filter(x -> x == "MoneySpent").findFirst().get()));
        totalMeters = new TotalMeters(statistics.get(statistics.keySet().stream().filter(x -> x == "TotalMeters").findFirst().get()));
        return statistics;
    }

    /**
     * Method that writes new statistics on file.
     * @throws IOException
     */
    public static void uploadSaves() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(deaths.getName() + ";" + deaths.getValue() + "\n");
        writer.write(killedNpc.getName() + ";" + killedNpc.getValue() + "\n");
        writer.write(grabbedMoney.getName() + ";" + grabbedMoney.getValue() + "\n");
        writer.write(grabbedObjects.getName() + ";" + grabbedObjects.getValue() + "\n");
        writer.write(maxMeters.getName() + ";" + maxMeters.getValue() + "\n");
        writer.write(maxMoney.getName() + ";" + maxMoney.getValue() + "\n");
        writer.write(moneySpent.getName() + ";" + moneySpent.getValue() + "\n");
        writer.write(totalMeters.getName() + ";" + totalMeters.getValue() + "\n");
        writer.close();
    }

}
