package it.unibo.jetpackjoyride.graphics.impl;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        this.setLayout(new GridLayout());
        JButton btnStart = new JButton("Start");
        JButton btnShop = new JButton("Shop");
        JButton btnStatistics = new JButton("Statistics");
        this.add(btnStatistics);
        this.add(btnShop);
        this.add(btnStart);

        btnStart.addActionListener(e -> {
            this.setVisible(false);
        });
    }

}
