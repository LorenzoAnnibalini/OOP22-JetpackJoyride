package it.unibo.jetpackjoyride.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.core.api.GadgetLoader;
import it.unibo.jetpackjoyride.core.impl.GadgetLoaderImpl;
import it.unibo.jetpackjoyride.model.api.Gadget;
import it.unibo.jetpackjoyride.model.api.Hitbox;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.model.impl.HitboxImpl;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;

public class PlayerImplTest {

    private final Point2d position = new Point2d(30, 250);
    private final Vector2d velocity = new Vector2d(30, 250);
    private final Hitbox hitbox = new HitboxImpl(5, 5, position);
    PlayerImpl player = new PlayerImpl(this.position, this.velocity, this.hitbox);
    
    @Test
    void testApplyGadget(){
        //Load gadget
        GadgetLoader gadgetLoader = new GadgetLoaderImpl();
        try {
            gadgetLoader.downloadGadget();
        } catch (FileNotFoundException e){}

        //Check if the player constuction was successful
        assertEquals(30, player.getCurrentPos().x);
        assertEquals(250, player.getCurrentVel().y);

        /*Fist two test for the applyGadget() method, in first case the gadget
        * is not active, in the second case the gadget is active*/
        player.setDirectionDOWN();
        assertEquals(254, player.getCurrentVel().y);

        player.setDirectionUP();
        assertEquals(244.8, player.getCurrentVel().y);

        /*Set to active also the second gadget*/
        Gadget gadget = new GadgetImpl();
        gadget.setValue("Gravity Belt", "true", "true", "150$", "Aumento gravita'");
        
    
        player.setDirectionSTATIC();
        assertEquals(250, player.getCurrentVel().y);

        /*Retry of the first testbut now with the gadget state active */
        player.setDirectionDOWN();
        assertEquals(255.2, player.getCurrentVel().y);

        
    }
}
