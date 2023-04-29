package it.unibo.jetpackjoyride.input.api;

import java.util.Optional;

/**
 * Interface for the input type.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public interface Input {

    /**
     * Enum for the type of the input.
     */
    enum typeInput {
        /**
         * Input type for raise the player.
         */
        UP,

        /**
         * Input type for open the menu.
         */
        MENU,

        /**
         * Input type for open the shop.
         */
        SHOP,

        /**
         * Input type for close the game.
         */
        EXIT,

        /**
         * Input type for select the gadget.
         */
        ENABLE,

        /**
         * Input type for deselect the gadget.
         */
        DISABLE,

        /**
         * Input type for buy the gadget.
         */
        BUY,

        /**
         * Input type for select the skin.
         */
        SELECT_SKIN,

        /**
         * Input type for buy the skin.
         */
        BUY_SKIN 
    }

    /**
     * Get the type of the input.
     * 
     * @return the type of the input.
     */
    public typeInput getType();

    /**
     * Get the name of the input.
     * 
     * @return an optional of the name of the input.
     */
    public Optional<String> getName();

}
