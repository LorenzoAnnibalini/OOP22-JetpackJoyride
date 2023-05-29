package it.unibo.jetpackjoyride.graphics.impl;

import java.util.HashMap;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.core.impl.SavesImpl;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.api.Input.TypeInput;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

/**
 * Class to visualize the statistics of the game.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public final class StatisticsPanel extends JPanel {
    private final transient InputQueue inputQueue;
    private final transient Saves saves;
    private static final float FONT_SIZE = 30f;
    private Map<String, Integer> statsMap;
    private final JTextArea statsArea;
    private static final String SEPARATOR = " : ";
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of the class.
     * 
     * @param inputQueue the input queue
     * @param font       the font for labels and buttons
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Input queue is meant to be the same for all the objects")
    public StatisticsPanel(final InputQueue inputQueue, final Font font) {
        super();
        this.setLayout(new BorderLayout());
        this.inputQueue = inputQueue;
        final JButton menu;
        this.statsArea = new JTextArea();
        this.statsArea.setEditable(false);
        this.statsArea.setFont(font.deriveFont(FONT_SIZE));
        menu = new JButton("Menu");
        menu.addActionListener(e -> {
            this.inputQueue.addInput(new InputImpl(TypeInput.MENU, null));
        });
        menu.setFont(font);
        this.add(menu, BorderLayout.SOUTH);
        final JPanel boxPanel = new JPanel(new FlowLayout());
        boxPanel.add(statsArea, BorderLayout.CENTER);
        this.setBackground(Color.BLACK);
        boxPanel.setBackground(Color.BLACK);
        this.add(boxPanel, BorderLayout.CENTER);
        saves = new SavesImpl();
        this.statsMap = new HashMap<>();
    }

    // @SuppressFBWarnings
    /**
     * Method to update the statistics panel.
     * 
     * @throws FileNotFoundException if the file is not found
     * @throws IOException           if there is an error in the file
     */
    public void update() throws FileNotFoundException, IOException {
        statsMap.clear();
        statsMap = saves.downloadSaves();
        final String statsText = StatisticsImpl.ACTUAL_MONEY.getY() + SEPARATOR
                + statsMap.get(StatisticsImpl.ACTUAL_MONEY.getX()) + "\n" + StatisticsImpl.DEATHS.getY() + SEPARATOR
                + statsMap.get(StatisticsImpl.DEATHS.getX()) + "\n" + StatisticsImpl.MAX_MONEY.getY() + SEPARATOR
                + statsMap
                        .get(StatisticsImpl.MAX_MONEY.getX())
                + "\n" + StatisticsImpl.GRABBED_MONEY.getY() + SEPARATOR + statsMap
                        .get(StatisticsImpl.GRABBED_MONEY.getX())
                + "\n" + StatisticsImpl.GRABBED_OBJECTS.getY()
                + SEPARATOR + statsMap.get(StatisticsImpl.GRABBED_OBJECTS.getX()) + "\n"
                + StatisticsImpl.KILLED_NPC.getY() + SEPARATOR + statsMap.get(StatisticsImpl.KILLED_NPC.getX())
                + "\n" + StatisticsImpl.MAX_METERS.getY() + SEPARATOR
                + statsMap.get(StatisticsImpl.MAX_METERS.getX()) + "\n" + StatisticsImpl.MAX_MONEY.getY()
                + SEPARATOR + statsMap.get(StatisticsImpl.MAX_MONEY.getX()) + "\n"
                + StatisticsImpl.MONEY_SPENT.getY() + SEPARATOR
                + statsMap.get(StatisticsImpl.MONEY_SPENT.getX()) + "\n" + StatisticsImpl.TOTAL_METERS.getY()
                + SEPARATOR + statsMap.get(StatisticsImpl.TOTAL_METERS.getX()) + "\n";
        statsArea.setText(statsText);
    }
}
