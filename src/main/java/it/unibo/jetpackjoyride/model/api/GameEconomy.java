package it.unibo.jetpackjoyride.model.api;

import java.io.IOException;

/**
 * Interface GameEconomy to manage the game economy,
 * i.e. all the items that can be purchased from the shop
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public interface GameEconomy {

    /**
     * Method to manage the purchase of a gadget,
     * this method must count the available money and
     * allow or not to buy the gadget.
     * 
     * @param name of the gadget
     */
    void buyGadget(String name) throws IOException;

    /**
     * Method to manage the activation of a gadget,
     * this method must check if the gadget is purchased
     * before to activate it.
     * 
     * @param name of the gadget
     */
    void enableGadget(String name);

    /**
     * Method to manage the deactivation of a gadget.
     * 
     * @param name of the gadget
     */
    void disableGadget(String name);

    /**
     * Method to manage the purchase of a skin,
     * this method must count the available money and
     * allow or not to buy the skin.
     * 
     * @param name of the skin
     */
    void buySkin(String name) throws IOException;

    /**
     * Method to manage the selection of a skin,
     * this method must check if the skin is purchased
     * before to select it, and if it is purchased
     * must also deselect the skin currently selected.
     * 
     * @param name of the skin
     */
    void selectSkin(String name);

}
