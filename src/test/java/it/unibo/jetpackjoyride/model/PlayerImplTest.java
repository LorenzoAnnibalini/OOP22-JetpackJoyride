package it.unibo.jetpackjoyride.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Gadget;
import it.unibo.jetpackjoyride.model.api.Hitbox;
import it.unibo.jetpackjoyride.model.api.Saves;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.model.impl.HitboxImpl;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;
import it.unibo.jetpackjoyride.model.impl.SavesImpl;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

/**
 * JUnit test for the PlayerImpl class.
 *
 * @author lorenzo.bacchini4@studio.unibo.it
 */
class PlayerImplTest {

    private final Point2d position = new Point2d(30, 250);
    private final Vector2d velocity = new Vector2d(30, 250);
    private final Hitbox hitbox = new HitboxImpl(5.0, 5.0, position);
    private final Saves saves = new SavesImpl();
    private final StatisticsImpl statistics = new StatisticsImpl();
    private final PlayerImpl player;
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    /**
     * Constructor of the class PlayerImplTest.
     */
    PlayerImplTest() {
        try {
            statistics.setAll(saves.downloadSaves());
        } catch (final IOException e) {
            throw new IllegalStateException("Error downloading statistics", e);
        }
        player = new PlayerImpl(this.position, this.velocity, this.hitbox, this.statistics);
    }

    @Test
    void testApplyGadget() {
        // Load gadget
        final Gadget gadget = new GadgetImpl();
        final Map<String, List<String>> gadgetMap = new HashMap<>();
        // CHECKSTYLE: MagicNumber OFF
        /* rule deactivated because these are all values ​​of the speed y at different instants, 
         *it would be redundant to create a variable for each possible value
         */
        final Queue<Double> expectedYValue = new LinkedList<>(List.of(160d, -188.5d, 0d, 208d)); 
        // CHECKSTYLE: MagicNumber ON
        gadgetMap.put("Air Barry", List.of(TRUE, TRUE, "100", "Moltiplicatore di salto iniziale"));
        gadgetMap.put("Gravity Belt", List.of(FALSE, TRUE, "150", "Aumento gravita'"));
        GadgetImpl.setAll(gadgetMap);

        // Check if the player constuction was successful
        assertEquals(this.position.getX(), player.getCurrentPos().getX());
        assertEquals(this.position.getY(), player.getCurrentVel().getY());

        /*
         * First two test for the applyGadget() method, in first case the gadget
         * is not active, in the second case the gadget is active
         */
        player.setDirectionDOWN();
        assertEquals(expectedYValue.poll(), player.getCurrentVel().getY());

        player.setDirectionUP();
        assertEquals(expectedYValue.poll(), player.getCurrentVel().getY());

        /* Set to active also the second gadget */
        gadget.setValue("Gravity Belt", TRUE, TRUE, "150$", "Aumento gravita'");

        player.setDirectionSTATIC();
        assertEquals(expectedYValue.poll(), player.getCurrentVel().getY());

        /* Retry of the first testbut now with the gadget state active */
        player.setDirectionDOWN();
        assertEquals(expectedYValue.poll(), player.getCurrentVel().getY());

    }
}
