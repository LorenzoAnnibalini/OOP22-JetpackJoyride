package it.unibo.jetpackjoyride.input.api;

/**
 * Interface for the input type.
 * @author mattia.burreli@studio.unibo.it
 */
public interface Input {
    
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

public typeInput getType();

}
