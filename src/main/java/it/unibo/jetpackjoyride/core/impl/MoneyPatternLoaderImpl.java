package it.unibo.jetpackjoyride.core.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.core.api.MoneyPatternLoader;
import it.unibo.jetpackjoyride.model.api.Hitbox;
import it.unibo.jetpackjoyride.model.impl.HitboxImpl;
import it.unibo.jetpackjoyride.model.impl.Money;

/**
 * A class to load money from file.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class MoneyPatternLoaderImpl implements MoneyPatternLoader{
    
    private final String SEPARATOR = File.separator;
    private final int minAvailableFile = 1;
    /*Order to read data from nextLine().*/
    private final int X = 0;
    private final int Y = 1;
    /*Attribute that count the number of available pattern file.*/
    private final int availableFile = 1;
    private final String fileNumber;
    String filename = "resources" + this.SEPARATOR + "money";

    /**
     * Constructor of the class MoneyPatternLoader.
     */
    public MoneyPatternLoaderImpl() {
        if (this.availableFile < this.minAvailableFile) {
            throw new IllegalArgumentException("The number of available file is less than the minimum number of available file.");
        }
            this.fileNumber = Integer.toString((int) Math.floor(Math.random()
                    * (this.availableFile - this.minAvailableFile + 1)
                    + this.minAvailableFile));
    }

    public ArrayList<Money> getMoneyPattern() throws FileNotFoundException{
        ArrayList<Money> moneyList = new ArrayList<>();
        Scanner filePattern = new Scanner(new File(this.filename + this.fileNumber + ".txt"));
        while (filePattern.hasNextLine()) {
            String line = filePattern.nextLine();
            int x =  Integer.parseInt(line.split(",")[X]);
            int y =  Integer.parseInt(line.split(",")[Y]);
            Point2d pos = new Point2d(x, y);
            Vector2d vec = new Vector2d(0, y);
            Hitbox hitbox = new HitboxImpl(5, 5, pos);
            moneyList.add(new Money(pos, vec, hitbox));
        }
        filePattern.close();
        return moneyList;
    }
}
