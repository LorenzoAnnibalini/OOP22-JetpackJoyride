package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that represents the panel of the shop.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class ShopPanel extends JPanel{
    
    public ShopPanel(HashMap<String, String> valori) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (String key : valori.keySet()) {
            JPanel panel = new JPanel(new FlowLayout());
            panel.add(new JLabel(key));
            panel.add(new JButton(valori.get(key)));
            this.add(panel); 
        }
    }


}
