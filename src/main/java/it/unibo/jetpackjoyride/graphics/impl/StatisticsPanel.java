package it.unibo.jetpackjoyride.graphics.impl;

import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.core.impl.SavesImpl;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.api.Input.typeInput;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

/**
 * Class to visualize the statistics of the game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public final class StatisticsPanel extends JPanel {
    private final InputQueue inputQueue;
    private final Saves saves;

    /**
     * Constructor of the class.
     * 
     * @param inputQueue the input queue
     */
    public StatisticsPanel(final InputQueue inputQueue) {
        super();
        this.setLayout(new BorderLayout());
        this.inputQueue = inputQueue;
        final JButton menu;
        menu = new JButton("Menu");
        menu.addActionListener(e -> {
            this.inputQueue.addInput(new InputImpl(typeInput.MENU, null));
        });
        this.add(menu, BorderLayout.SOUTH);
        saves = new SavesImpl();
    }

    /**
     * Method to update the statistics panel.
     * 
     * @throws FileNotFoundException if the file is not found
     * @throws IOException           if there is an error in the file
     */
    public void update() throws FileNotFoundException, IOException {
        Map<String, Integer> statsMap = new HashMap<>();
        JPanel boxPanel = new JPanel(new FlowLayout());
        statsMap = saves.downloadSaves();
        String statsText = "<html>";
        /*
         * for (int i = 0; i< statsMap.size(); i++) {
         * int value = statsMap.get(i);
         * String statName = StatisticsImpl.class.getFields()[i].getName();
         * statsText = statsText + statName + ": " + value + "<br>";
         * }
         */
        /*
         * for (String stat : statsMap.keySet()) {
         * int value = statsMap.get(stat);
         * var statName = StatisticsImpl.class.getFields()[0].getName();
         * StatisticsImpl.ACTUAL_MONEY.getY();
         * String statName = stat;
         * statsText = statsText + statName + ": " + value + "<br>";
         * }
         */
        statsText = statsText + StatisticsImpl.ACTUAL_MONEY.getY() + " : "
                + statsMap.get(StatisticsImpl.ACTUAL_MONEY.getX()) + "<br>";
        statsText = statsText + StatisticsImpl.DEATHS.getY() + " : " + statsMap.get(StatisticsImpl.DEATHS.getX())
                + "<br>";
        statsText = statsText + StatisticsImpl.MAX_MONEY.getY() + " : " + statsMap.get(StatisticsImpl.MAX_MONEY.getX())
                + "<br>";
        statsText = statsText + StatisticsImpl.GRABBED_MONEY.getY() + " : "
                + statsMap.get(StatisticsImpl.GRABBED_MONEY.getX()) + "<br>";
        statsText = statsText + StatisticsImpl.GRABBED_OBJECTS.getY() + " : "
                + statsMap.get(StatisticsImpl.GRABBED_OBJECTS.getX()) + "<br>";
        statsText = statsText + StatisticsImpl.KILLED_NPC.getY() + " : "
                + statsMap.get(StatisticsImpl.KILLED_NPC.getX()) + "<br>";
        statsText = statsText + StatisticsImpl.MAX_METERS.getY() + " : "
                + statsMap.get(StatisticsImpl.MAX_METERS.getX()) + "<br>";
        statsText = statsText + StatisticsImpl.MAX_MONEY.getY() + " : " + statsMap.get(StatisticsImpl.MAX_MONEY.getX())
                + "<br>";
        statsText = statsText + StatisticsImpl.MONEY_SPENT.getY() + " : "
                + statsMap.get(StatisticsImpl.MONEY_SPENT.getX()) + "<br>";
        statsText = statsText + StatisticsImpl.TOTAL_METERS.getY() + " : "
                + statsMap.get(StatisticsImpl.TOTAL_METERS.getX()) + "<br>";

        statsText = statsText + "</html>";
        JLabel label = new JLabel(statsText);
        boxPanel.add(label, BorderLayout.CENTER);
        this.add(boxPanel, BorderLayout.CENTER);
    }
}
