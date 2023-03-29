package it.unibo.jetpackjoyride.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

public class Saves{

    private final static String SEPARATOR = File.separator;

    public static Statistics downloadSaves() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("resources"+SEPARATOR+"saves.csv"));
        List<Integer> retValues = new ArrayList<>();
        sc.useDelimiter(";");
        sc.nextLine();//skip the first line of csv file because there are  the intestation of columns
        while(sc.hasNext()) {
            int value = Integer.parseInt(sc.next());
            retValues.add(value);
        }
        sc.close(); 
        Statistics statistics = new StatisticsImpl(retValues.get(0),retValues.get(1),retValues.get(0),retValues.get(0),retValues.get(0),retValues.get(0),retValues.get(0),retValues.get(0));
        return statistics;
    }

    public static void uploadSaves() {
        //Statistics stats = new StatisticsImpl();

    }
    
}
