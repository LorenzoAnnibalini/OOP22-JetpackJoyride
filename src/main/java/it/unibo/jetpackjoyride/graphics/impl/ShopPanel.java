package it.unibo.jetpackjoyride.graphics.impl;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.jetpackjoyride.model.api.Gadget;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.model.api.SkinInfo;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.impl.SkinInfoImpl;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.api.Input.TypeInput;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.core.api.GadgetInfoPositions;
import it.unibo.jetpackjoyride.core.api.SkinInfoPositions;

/**
 * Class that represents the panel of the shop.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class ShopPanel extends JPanel {

    private final JButton menu;
    private final InputQueue inputQueue;
    private final Gadget gadget;
    private final SkinInfo skinInfo;
    private final JLabel moneyLabel;
    private final HashMap<String, ArrayList<JButton>> buttonMapGadget;
    private final HashMap<String, ArrayList<JButton>> buttonMapSkin;
    private final SpriteLoader spriteLoader;
    private final Statistics generalStatistics;
    private Font font;

    /**
     * Constructor for the ShopPanel.
     * 
     * @param inputQueue
     * @param generalStatistics
     */
    public ShopPanel(final InputQueue inputQueue, final Statistics generalStatistics, Font font) {
        super();
        this.inputQueue = inputQueue;
        this.spriteLoader = new SpriteLoader();
        this.generalStatistics = generalStatistics;
        this.font = font;
        this.buttonMapGadget = new HashMap<>();
        this.buttonMapSkin = new HashMap<>();
        this.setLayout(new BorderLayout());
        final JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        this.moneyLabel = new JLabel("YourMoney: " + this.getActualMoney());
        this.moneyLabel.setFont(font);
        this.add(moneyLabel, BorderLayout.LINE_START);
        JLabel gadgetLabel = new JLabel("Gadget");
        gadgetLabel.setFont(font);
        boxPanel.add(gadgetLabel);
        this.add(boxPanel, BorderLayout.NORTH);

        this.menu = new JButton("Menu");
        this.menu.setFont(font);
        this.menu.addActionListener(e -> {
            this.inputQueue.addInput(new InputImpl(TypeInput.MENU, null));
        });
        this.add(menu, BorderLayout.SOUTH);

        gadget = new GadgetImpl();
        for (final String name : gadget.getAll().keySet()) {
            final String state = gadget.getValue(name).get(GadgetInfoPositions.STATE.ordinal());
            final String purchased = gadget.getValue(name).get(GadgetInfoPositions.PURCHASED.ordinal());
            final String price = gadget.getValue(name).get(GadgetInfoPositions.PRICE.ordinal());
            final String description = gadget.getValue(name).get(GadgetInfoPositions.DESCRIPTION.ordinal());

            final JPanel flowPanel = new JPanel(new FlowLayout());
            JLabel nameLabel = new JLabel(name);
            JLabel priceLabel = new JLabel(price + "$");
            JLabel descriptionLabel = new JLabel(description);
            nameLabel.setFont(font.deriveFont(20f));
            priceLabel.setFont(font.deriveFont(20f));
            descriptionLabel.setFont(font.deriveFont(20f));
            flowPanel.add(nameLabel);
            flowPanel.add(priceLabel);
            flowPanel.add(descriptionLabel);
            final JButton enableButton = createGadgetButton(Boolean.parseBoolean(state) ? "Disable" : "Enable",
                    true, name);
            final JButton purchasedButton = createGadgetButton("Purchased", !Boolean.parseBoolean(purchased), name);
            buttonMapGadget.put(name, new ArrayList<>(List.of(enableButton, purchasedButton)));
            flowPanel.add(purchasedButton);
            flowPanel.add(enableButton);
            boxPanel.add(flowPanel);
        }

        JLabel skinLabel = new JLabel("Skin");
        skinLabel.setFont(font);
        boxPanel.add(skinLabel);
        skinInfo = new SkinInfoImpl();
        for (final String name : skinInfo.getAll().keySet()) {
            final String state = skinInfo.getValue(name).get(SkinInfoPositions.STATE.ordinal());
            final String purchased = skinInfo.getValue(name).get(SkinInfoPositions.PURCHASED.ordinal());
            final String price = skinInfo.getValue(name).get(SkinInfoPositions.PRICE.ordinal());

            final JPanel flowPanel = new JPanel(new FlowLayout());
            JLabel nameLabel = new JLabel(name);
            JLabel priceLabel = new JLabel(price + "$");
            nameLabel.setFont(font.deriveFont(20f));
            priceLabel.setFont(font.deriveFont(20f));
            flowPanel.add(nameLabel);
            flowPanel.add(priceLabel);
            final JButton enableButton = createSkinButton("Enable", !Boolean.parseBoolean(state), name);
            final JButton purchasedButton = createSkinButton("Purchased", !Boolean.parseBoolean(purchased), name);
            buttonMapSkin.put(name, new ArrayList<>(List.of(enableButton, purchasedButton)));

            flowPanel.add(loadSpriteImage(name));
            flowPanel.add(purchasedButton);
            flowPanel.add(enableButton);
            boxPanel.add(flowPanel);
        }
    }

    /**
     * method to return only the statistic
     * that represent the actual money of the player.
     * 
     * @return the actual money of the player
     */
    private int getActualMoney() {
        return this.generalStatistics.getValue("ActualMoney");
    }

    /**
     * This method load the sprite image from the spriteLoader.
     * 
     * @param name of the sprite to load
     * @return the JLabel with the sprite image
     */
    private JLabel loadSpriteImage(final String name) {
        final Sprite skinSprite = spriteLoader.getSprites().get(name);
        skinSprite.scale();
        final Image skinImage = skinSprite.getScaled();
        final JLabel skinLabel = new JLabel(new ImageIcon(skinImage));
        return skinLabel;
    }

    /**
     * This method create a button for the gadget.
     * 
     * @param text    of the button
     * @param enabled state of the gadget
     * @param name    of the gadget
     * @return the Jbutton created
     */
    private JButton createGadgetButton(final String text, final boolean enabled, final String name) {
        final JButton button = new JButton(text);
        button.setFont(font.deriveFont(20f));
        button.setEnabled(enabled);
        button.addActionListener(e -> {
            switch (button.getText()) {
                case "Enable":
                    this.inputQueue.addInput(new InputImpl(TypeInput.ENABLE, name));
                    break;
                case "Disable":
                    this.inputQueue.addInput(new InputImpl(TypeInput.DISABLE, name));
                    break;
                case "Purchased":
                    this.inputQueue.addInput(new InputImpl(TypeInput.BUY, name));
                    break;
                default:
                    break;
            }
        });
        return button;
    }

    /**
     * This method create a button for the gadget.
     * 
     * @param text    of the button
     * @param enabled state of the gadget
     * @param name    of the gadget
     * @return the JButton created
     */
    private JButton createSkinButton(final String text, final boolean enabled, final String name) {
        final JButton button = new JButton(text);
        button.setFont(font.deriveFont(20f));
        button.setEnabled(enabled);
        button.addActionListener(e -> {
            switch (button.getText()) {
                case "Enable":
                    this.inputQueue.addInput(new InputImpl(TypeInput.SELECT_SKIN, name));
                    break;
                case "Purchased":
                    this.inputQueue.addInput(new InputImpl(TypeInput.BUY_SKIN, name));
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
    public void update() {
        for (final String name : buttonMapGadget.keySet()) {
            final ArrayList<JButton> buttonList = buttonMapGadget.get(name);
            final String purchased = gadget.getValue(name).get(GadgetInfoPositions.PURCHASED.ordinal());
            final String state = (Boolean
                    .parseBoolean(gadget.getValue(name).get(GadgetInfoPositions.STATE.ordinal())) ? "Disable"
                            : "Enable");
            buttonList.get(GadgetInfoPositions.PURCHASED.ordinal()).setEnabled(!Boolean.parseBoolean(purchased));
            buttonList.get(GadgetInfoPositions.STATE.ordinal()).setText(state);
        }
        for (final String name : buttonMapSkin.keySet()) {
            final ArrayList<JButton> buttonList = buttonMapSkin.get(name);
            final String purchased = skinInfo.getValue(name).get(SkinInfoPositions.PURCHASED.ordinal());
            final String state = skinInfo.getValue(name).get(SkinInfoPositions.STATE.ordinal());
            buttonList.get(SkinInfoPositions.PURCHASED.ordinal()).setEnabled(!Boolean.parseBoolean(purchased));
            buttonList.get(SkinInfoPositions.STATE.ordinal()).setEnabled(!Boolean.parseBoolean(state));
        }
        this.moneyLabel.setText("YourMoney: " + this.getActualMoney());
    }

}
