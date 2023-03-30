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
    private final static int NAME_POSITION = 0;
    private final static int VALUE_POSITION = 1;
    private final static String filename = "resources" + SEPARATOR + "saves.csv";
    private static Deaths deaths;
    private static KilledNpc killedNpc;
    private static GrabbedMoney grabbedMoney;
    private static GrabbedObjects grabbedObjects;
    private static MaxMeters maxMeters;
    private static MaxMoney maxMoney;
    private static MoneySpent moneySpent;
    private static TotalMeters totalMeters;

    /**
     * Getter method for deaths.
     * 
     * @return number of deaths
     */
    public static Deaths getDeaths() {
        return deaths;
    }

    /**
     * Getter method for killed npc.
     * 
     * @return number of killed npc
     */
    public static KilledNpc getKilledNpc() {
        return killedNpc;
    }

    /**
     * Getter method for grabbed money.
     * 
     * @return amount of grabbed money
     */
    public static GrabbedMoney getGrabbedMoney() {
        return grabbedMoney;
    }

    /**
     * Getter method for grabbed objects.
     * 
     * @return number of grabbed objects
     */
    public static GrabbedObjects getGrabbedObjects() {
        return grabbedObjects;
    }

    /**
     * Getter method for max meters.
     * 
     * @return number of max meters
     */
    public static MaxMeters getMaxMeters() {
        return maxMeters;
    }

    /**
     * Getter method for max money.
     * 
     * @return amount of max money
     */
    public static MaxMoney getMaxMoney() {
        return maxMoney;
    }

    /**
     * Getter method for money spent.
     * 
     * @return amount of money spent
     */
    public static MoneySpent getMoneySpent() {
        return moneySpent;
    }

    /**
     * Getter method for total meters.
     * 
     * @return number of total meters
     */
    public static TotalMeters getTotalMeters() {
        return totalMeters;
    }

    /**
     * Method to download datas from csv file.
     * 
     * @return a map that maps every stat name with his value
     * @throws FileNotFoundException
     */
    public static Map<String, Integer> downloadSaves() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        Map<String, Integer> statistics = new HashMap<>();
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            String name = data.split(";")[NAME_POSITION];
            int value = Integer.parseInt(data.split(";")[VALUE_POSITION]);
            statistics.put(name, value);
        }
        sc.close();
        // Creation of statistics objects
        deaths = new Deaths(fillValue(statistics, "Deaths"), "Deaths");
        grabbedMoney = new GrabbedMoney(fillValue(statistics, "GrabbedMoney"), "GrabbedMoney");
        grabbedObjects = new GrabbedObjects(fillValue(statistics, "GrabbedObjects"), "GrabbedObjects");
        killedNpc = new KilledNpc(fillValue(statistics, "KilledNpc"), "KilledNpc");
        maxMeters = new MaxMeters(fillValue(statistics, "MaxMeters"), "MaxMeters");
        maxMoney = new MaxMoney(fillValue(statistics, "MaxMoney"), "MaxMoney");
        moneySpent = new MoneySpent(fillValue(statistics, "MoneySpent"), "MoneySpent");
        totalMeters = new TotalMeters(fillValue(statistics, "TotalMeters"), "TotalMeters");
        return statistics;
    }

    /**
     * Method that writes new statistics on file.
     * 
     * @throws IOException
     */
    public static void uploadSaves() throws IOException {
        // System.out.println(deaths.getClass() + ";" + deaths.getValue() + "\n");
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

    /**
     * Method to take the value of a statistics by his name.
     * 
     * @param stats the map of names and values
     * @param name  the name of statistc to take
     * @return value of statistic passed
     */
    private static int fillValue(Map<String, Integer> stats, String name) {
        return stats.get(stats.keySet().stream()
                .filter(x -> x.startsWith(name))
                .findAny()
                .get());
    }
}
