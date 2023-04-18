package it.unibo.jetpackjoyride.input.api;

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
        MENU
    }

    /**
     * Get the type of the input.
     * 
     * @return the type of the input
     */
    public typeInput getType();

}
