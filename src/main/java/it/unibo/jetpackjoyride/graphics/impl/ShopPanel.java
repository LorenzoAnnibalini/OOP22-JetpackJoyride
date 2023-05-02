package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.api.Input.typeInput;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.core.api.GadgetInfoPositions;
/**
 * Class that represents the panel of the shop.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class ShopPanel extends JPanel{
    
    private final JButton menu;
    private final InputQueue inputQueue;

    /**
     * Constructor for the ShopPanel.
     * @param inputQueue
     */
    public ShopPanel(final InputQueue inputQueue) {
        super();
        this.inputQueue = inputQueue;
        this.setLayout(new BorderLayout());
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.add(new JLabel("Gadget"));
        this.add(boxPanel, BorderLayout.NORTH);

        this.menu = new JButton("Menu");
        this.menu.addActionListener(e -> {
            this.inputQueue.addInput(new InputImpl(typeInput.MENU, null));
        });
        this.add(menu, BorderLayout.SOUTH);

        GadgetImpl gadget = new GadgetImpl();
        for (String key : gadget.getAll().keySet()) {
            String state = gadget.getValue(key).get(GadgetInfoPositions.STATE.ordinal());
            String purchased = gadget.getValue(key).get(GadgetInfoPositions.PURCHASED.ordinal());
            String price = gadget.getValue(key).get(GadgetInfoPositions.PRICE.ordinal());
            String description = gadget.getValue(key).get(GadgetInfoPositions.DESCRIPTION.ordinal());

            JPanel FlowPanel = new JPanel(new FlowLayout());
            FlowPanel.add(new JLabel(key));
            FlowPanel.add(new JLabel(price));
            FlowPanel.add(new JLabel(description));
            FlowPanel.add(createGadgetButton("Purchased", !Boolean.parseBoolean(purchased), key));
            FlowPanel.add(createGadgetButton(Boolean.parseBoolean(state) == true ? "Disable" : "Enable", true, key));
            boxPanel.add(FlowPanel);
        }

        boxPanel.add(new JLabel("Skin"));
    }

    private JButton createGadgetButton(String text, boolean enabled, String name){
        JButton button = new JButton(text);
        button.setEnabled(enabled);
        button.addActionListener(e -> {
            switch (button.getText()) {
            case "Enable":
                button.setText("Disable");
                this.inputQueue.addInput(new InputImpl(typeInput.ENABLE, name));
                break;
            case "Disable":
                button.setText("Enable");
                this.inputQueue.addInput(new InputImpl(typeInput.DISABLE, name));
                break;
            case "Purchased":
                button.setEnabled(false);
                this.inputQueue.addInput(new InputImpl(typeInput.BUY, null));
                break;
            default:
                break;
            }
        });
        return button;
    }

}
