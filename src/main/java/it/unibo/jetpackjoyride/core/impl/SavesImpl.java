package it.unibo.jetpackjoyride.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

public class SavesImpl implements Saves{

    private String SEPARATOR = File.separator;

    @Override
    public List<Integer> downloadSaves() throws FileNotFoundException {
        List<Integer> retValues = new ArrayList<>();
        Scanner sc = new Scanner(new File("resources"+SEPARATOR+"saves.csv"));
        sc.useDelimiter(";");
        sc.nextLine();//skip the first line of csv file because there are the intestation of column
        while(sc.hasNext()) {
            int value = Integer.parseInt(sc.next());
            System.out.println(value);
            retValues.add(value);
        }
        sc.close();        
        return retValues;
    }

    @Override
    public void uploadSaves() {
        //Statistics stats = new StatisticsImpl();

    }
    
}
