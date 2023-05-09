package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.jetpackjoyride.model.api.Gadget;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.model.api.SkinInfo;
import it.unibo.jetpackjoyride.model.impl.SkinInfoImpl;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.api.Input.typeInput;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.core.api.GadgetInfoPositions;
import it.unibo.jetpackjoyride.core.api.SkinInfoPositions;

/**
 * Class that represents the panel of the shop.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class ShopPanel extends JPanel{
    
    private final JButton menu;
    private final InputQueue inputQueue;
    private final Gadget gadget;
    private final SkinInfo skinInfo;
    private HashMap<String, ArrayList<JButton>> buttonMapGadget;
    private HashMap<String, ArrayList<JButton>> buttonMapSkin;

    /**
     * Constructor for the ShopPanel.
     * @param inputQueue
     */
    public ShopPanel(final InputQueue inputQueue) {
        super();
        this.inputQueue = inputQueue;
        this.buttonMapGadget = new HashMap<>();
        this.buttonMapSkin = new HashMap<>();
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

        gadget = new GadgetImpl();
        for (String name : gadget.getAll().keySet()) {
            String state = gadget.getValue(name).get(GadgetInfoPositions.STATE.ordinal());
            String purchased = gadget.getValue(name).get(GadgetInfoPositions.PURCHASED.ordinal());
            String price = gadget.getValue(name).get(GadgetInfoPositions.PRICE.ordinal());
            String description = gadget.getValue(name).get(GadgetInfoPositions.DESCRIPTION.ordinal());

            JPanel flowPanel = new JPanel(new FlowLayout());
            flowPanel.add(new JLabel(name));
            flowPanel.add(new JLabel(price));
            flowPanel.add(new JLabel(description));
            JButton enableButton = createGadgetButton(Boolean.parseBoolean(state) == true ? "Disable" : "Enable", true, name);
            JButton purchasedButton = createGadgetButton("Purchased", !Boolean.parseBoolean(purchased), name);
            buttonMapGadget.put(name, new ArrayList<>(List.of(enableButton, purchasedButton)));
            flowPanel.add(purchasedButton);
            flowPanel.add(enableButton);
            boxPanel.add(flowPanel);
        }
        
        boxPanel.add(new JLabel("Skin"));
        skinInfo = new SkinInfoImpl();
        for (String name : skinInfo.getAll().keySet()) {
            String state = skinInfo.getValue(name).get(SkinInfoPositions.STATE.ordinal());
            String purchased = skinInfo.getValue(name).get(SkinInfoPositions.PURCHASED.ordinal());

            JPanel flowPanel = new JPanel(new FlowLayout());
            flowPanel.add(new JLabel(name));
            JButton enableButton = createSkinButton("Enable", !Boolean.parseBoolean(state), name);
            JButton purchasedButton = createSkinButton("Purchased", !Boolean.parseBoolean(purchased), name);
            buttonMapSkin.put(name, new ArrayList<>(List.of(enableButton, purchasedButton)));
            flowPanel.add(purchasedButton);
            flowPanel.add(enableButton);
            boxPanel.add(flowPanel);
        }
    }

    /**
     * This method create a button for the gadget.
     * @param text of the button
     * @param enabled state of the gadget
     * @param name of the gadget
     * @return the Jbutton created
     */
    private JButton createGadgetButton(String text, boolean enabled, String name){
        JButton button = new JButton(text);
        button.setEnabled(enabled);
        button.addActionListener(e -> {
            switch (button.getText()) {
            case "Enable":
                this.inputQueue.addInput(new InputImpl(typeInput.ENABLE, name));
                break;
            case "Disable":
                this.inputQueue.addInput(new InputImpl(typeInput.DISABLE, name));
                break;
            case "Purchased":
                this.inputQueue.addInput(new InputImpl(typeInput.BUY, null));
                break;
            default:
                break;
            }
        });
        return button;
    }

    /**
     * This method create a button for the gadget.
     * @param text of the button
     * @param enabled state of the gadget
     * @param name of the gadget
     * @return the JButton created
     */
    private JButton createSkinButton(String text, boolean enabled, String name){
        JButton button = new JButton(text);
        button.setEnabled(enabled);
        button.addActionListener(e -> {
            switch (button.getText()) {
            case "Enable":
                this.inputQueue.addInput(new InputImpl(typeInput.SELECT_SKIN, name));
                break;
            case "Purchased":
                this.inputQueue.addInput(new InputImpl(typeInput.BUY_SKIN, name));
                break;
            default:
                break;
            }
        });
        return button;
    }

    /**
     * This method update the panel recalulating the button state
     * by reading new states from the gadget values and skin values.
     */
    public void update(){
        for (String name : buttonMapGadget.keySet()) {
            ArrayList<JButton> buttonList = buttonMapGadget.get(name);
            String purchased = gadget.getValue(name).get(GadgetInfoPositions.PURCHASED.ordinal());
            String state = (Boolean.parseBoolean(gadget.getValue(name).get(GadgetInfoPositions.STATE.ordinal())) == true ? "Disable" : "Enable");
            buttonList.get(GadgetInfoPositions.PURCHASED.ordinal()).setEnabled(!Boolean.parseBoolean(purchased));
            buttonList.get(GadgetInfoPositions.STATE.ordinal()).setText(state); 
        }
        for (String name : buttonMapSkin.keySet()) {
            ArrayList<JButton> buttonList = buttonMapSkin.get(name);
            String purchased = skinInfo.getValue(name).get(SkinInfoPositions.PURCHASED.ordinal());
            String state = skinInfo.getValue(name).get(SkinInfoPositions.STATE.ordinal());
            buttonList.get(SkinInfoPositions.PURCHASED.ordinal()).setEnabled(!Boolean.parseBoolean(purchased));
            buttonList.get(SkinInfoPositions.STATE.ordinal()).setEnabled(!Boolean.parseBoolean(state));
        }
    }

}
