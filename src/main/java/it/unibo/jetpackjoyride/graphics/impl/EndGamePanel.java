package it.unibo.jetpackjoyride.graphics.impl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;

import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.api.Input.TypeInput;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.model.impl.WorldGameStateImpl;

/**
 * Panel for the end of the game.
 * 
 * @author lorenzo.annibalini@studio.unibo.it
 */
public class EndGamePanel extends JPanel {

    private static final long serialVersionUID = 13L;

    // End Game panels
    private final JPanel statisticsPanel = new JPanel(new FlowLayout());
    private final JPanel mainPageComands = new JPanel();

    // Elements of the statistics panel
    private final WorldGameStateImpl worldGameState;

    // End Games buttons
    private final JButton exit = new JButton("Exit");
    private final JButton menu = new JButton("Menu");

    // Title of the main page
    private final JTextArea title = new JTextArea();
    private final JPanel titlePanel = new JPanel();
    static final float FLOAT_SIZE = 50f;
    static final float LABEL_FONT_SIZE = 30f;

    //Font
    private final Font font;

    /**
     * Constructor of the EndGamePanel.
     * @param inputHandler
     * @param worldGameState
     * @param font
     */
    public EndGamePanel(final InputQueue inputHandler, final WorldGameStateImpl worldGameState, final Font font) {

        this.font = font;
        this.exit.setFont(font);
        this.menu.setFont(font);
        // Main Page layout
        this.setLayout(new BorderLayout());

        // Set the worldGameState
        this.worldGameState = worldGameState;

        // Font of the title
        title.setEditable(false);
        title.setBackground(null);
        title.setFont(font.deriveFont(FLOAT_SIZE));
        titlePanel.add(title, Alignment.CENTER);
        final String welcomText = "End Game !!!";
        title.setText(welcomText);

        // Position of the panels in the mainPage
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.WEST);
        this.add(statisticsPanel, BorderLayout.CENTER);
        this.add(mainPageComands, BorderLayout.SOUTH);

        // mainPageComands.add(settings);
        mainPageComands.add(exit);
        mainPageComands.add(menu);

        // set visible to false
        this.setVisible(false);

        /* ------------------------ ACTION LISTENER ------------------------- */
        exit.addActionListener(e -> inputHandler.addInput(new InputImpl(TypeInput.EXIT, "Exit")));
        menu.addActionListener(e -> inputHandler.addInput(new InputImpl(TypeInput.MENU, "Menu")));

    }

    /**
     * Method that updates the statistics of the Enda Game Panel.
     * 
     */
    public void update() {
        final Map<String, Integer> statsMap = worldGameState.getPlayer().getStatistics().getAll();
        final JPanel boxPanel = new JPanel(new FlowLayout());
        final StringBuilder sb = new StringBuilder("<html>");
        for (final String statName : statsMap.keySet()) {
            final int value = statsMap.get(statName);
            sb.append(statName + " : " + value + "<br>");
        }
        sb.append("</html>");
        final JLabel label = new JLabel(sb.toString());
        label.setFont(this.font.deriveFont(LABEL_FONT_SIZE));
        boxPanel.add(label, BorderLayout.CENTER);
        this.add(boxPanel, BorderLayout.CENTER);
    }
}
