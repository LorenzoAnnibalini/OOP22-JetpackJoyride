package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.JFrame;

public class GamePanel extends JFrame {
    public GamePanel() {
        this.add(new MenuPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(this.getPreferredSize());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
