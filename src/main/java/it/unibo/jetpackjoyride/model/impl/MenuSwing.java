package it.unibo.jetpackjoyride.model.impl;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class MenuSwing {
    public MenuSwing() {
        JFrame f = new JFrame("Jetpack Joyride");
        JPanel p = new JPanel();
        JPanel p2 = new JPanel();
        JPanel titlePanel = new JPanel();
        JButton settings = new JButton("Settings");
        JButton exit = new JButton("Exit");
        JButton newGame = new JButton("New Game");
        JButton statistics = new JButton("Statistics");
        JTextArea title = new JTextArea("Welcome to Jetpack Joyride!");

        //Default settings of JFrame 
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setTitle("Jetpack Joyride");

        //Color and Position of the title and titlePanel
        titlePanel.add(title, Alignment.CENTER);
        title.setEditable(false);
        title.setBackground(null);
        title.setFont( new Font("Arial", Font.BOLD, 30));

        //Position of the panels
        f.add(titlePanel, BorderLayout.NORTH);
        f.add(p, BorderLayout.CENTER);
        f.add(p2, BorderLayout.SOUTH);

        //p panel
        p.setLayout(new GridLayout(2,1));
        p.add(newGame);
        p.add(statistics);

        //p2 panel
        p2.add(settings);
        p2.add(exit);

        // if press exit button close the programm
        /*
         * exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
            }
        });
         */
        exit.addActionListener(e -> System.exit(0));

    }
    
}