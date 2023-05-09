package it.unibo.jetpackjoyride.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import it.unibo.jetpackjoyride.core.impl.ReadWriteFile;
import it.unibo.jetpackjoyride.model.impl.Money;

public class TestMoneyLoad {
    
    public static void main(String[] args) {
        System.out.println("TestMoneyLoad");
        ReadWriteFile<String, String> readWrite = new ReadWriteFile<>("C:\\Users\\bacco\\Desktop\\OOP22-JetpackJoyride\\resources\\", "money1.txt");
        ArrayList<Money> moneyList = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        try{
            list = readWrite.readArrayList();
        }catch(FileNotFoundException e){}
        moneyList = Money.getMoneyFromStringList(list);
        moneyList.stream().forEach(e -> System.out.println(e.getCurrentPos()));
    }
}
