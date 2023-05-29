package it.unibo.jetpackjoyride.core.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class MoneyPatternLoaderImpl implements MoneyPatternLoader {

    /* Order to read data from nextLine(). */
    private static final int X = 0;
    private static final int Y = 1;
    private static final int LIMIT = 395;
    private static final String SEPARATOR = File.separator;
    /*
     * Attribute that count the number of available pattern file.
     * N.B the number of available file must be the same of the number
     * of file money_.txt in the resources folder.
     */
    private final int availableFile;
    private final int minAvailableFile;
    private static final String FILENAME = "src" + SEPARATOR
        + "main" + SEPARATOR
        + "resources" + SEPARATOR
        + "money";
    private static final int NFILE = 4;
    // Range to change the y coordinate of the money.
    private static final int RANGE = 150;
    // Random to multiply the range.
    private static final int MINRANDOM = -1;
    private static final int MAXRANDOM = 1;

    /**
     * Constructor of the class MoneyPatternLoader.
     * Normal Constructor, the programmer set the minAvailableFile
     * and the availableFile.
     * 
     * @throws IllegalArgumentException if the number of available file
     *                                  is less than the minimum number of available
     *                                  file.
     */
    public MoneyPatternLoaderImpl() {
        this.availableFile = NFILE;
        this.minAvailableFile = 1;
        if (this.availableFile < this.minAvailableFile) {
            throw new IllegalArgumentException(
                    "The number of available file is less than the minimum number of available file.");
        }
    }

    /**
     * Constructor of the class MoneyPatternLoader.
     * Specific Constructor to set the number of the
     * unique(1 file) available file.
     * 
     * @param num the number of the available file.
     */
    public MoneyPatternLoaderImpl(final int num) {
        this.minAvailableFile = num;
        this.availableFile = num;
    }

    @Override
    public final ArrayList<Money> getMoneyPattern() {
        String fileNumber;
        fileNumber = Integer.toString((int) Math.floor(Math.random()
                * (this.availableFile - this.minAvailableFile + 1)
                + this.minAvailableFile));

        final ArrayList<Money> moneyList = new ArrayList<>();
        final double multiplier = Math.floor(Math.random() * (MAXRANDOM - MINRANDOM) + MINRANDOM);
        try (Scanner scanner = new Scanner(new File(FILENAME + fileNumber + ".txt"), StandardCharsets.UTF_8.name())) {
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                final int x = Integer.parseInt(line.split(",")[X]);
                int y = Integer.parseInt(line.split(",")[Y]);
                y = y + (int) (multiplier * RANGE);
                final Point2d startPosition = new Point2d(x, y);
                final Point2d finishPosition = new Point2d(x - LIMIT, startPosition.getY());
                final Vector2d vec = new Vector2d(finishPosition, startPosition);
                final Hitbox hitbox = new HitboxImpl(15.0, 15.0, startPosition);
                moneyList.add(new Money(startPosition, vec, hitbox));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error while reading Money Pattern " + fileNumber + "file", e);
        }
        return moneyList;
    }
}

