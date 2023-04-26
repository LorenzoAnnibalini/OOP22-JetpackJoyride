package it.unibo.jetpackjoyride.graphics;

import java.util.HashMap;

import javax.swing.JFrame;

import it.unibo.jetpackjoyride.graphics.impl.ShopPanel;

/**
 * Class to test the GUI of the shop.
 * 
 * @author lorenzo.bacchini4@tudio.unibo.it
 */
public class ShopTest {
    
    public static void main(String[] args) {
        HashMap<String, String> valori = new HashMap<>();
        valori.put("Barry shoes", "prova");
        valori.put("Barry shoes1", "prova1");
        valori.put("Barry shoes2", "prova2");
        JFrame frame = new JFrame();
        frame.setTitle("GUI shop test");
        frame.setSize(300, 400);
        frame.getContentPane().add(new ShopPanel(valori));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
