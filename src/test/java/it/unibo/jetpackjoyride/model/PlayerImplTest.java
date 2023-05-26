package it.unibo.jetpackjoyride.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.core.impl.SavesImpl;
import it.unibo.jetpackjoyride.model.api.Gadget;
import it.unibo.jetpackjoyride.model.api.Hitbox;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.model.impl.HitboxImpl;
import it.unibo.jetpackjoyride.model.impl.PlayerImpl;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

public class PlayerImplTest {

    private final Point2d position = new Point2d(30, 250);
    private final Vector2d velocity = new Vector2d(30, 250);
    private final Hitbox hitbox = new HitboxImpl(5, 5, position);
    final Saves saves = new SavesImpl();
    final StatisticsImpl statistics = new StatisticsImpl();
    PlayerImpl player;

    void createPlayer() {
        try {
            statistics.setAll(saves.downloadSaves());
        } catch (final Exception e) {
        }
        player = new PlayerImpl(this.position, this.velocity, this.hitbox, this.statistics);
    }

    @Test
    void testApplyGadget() {
        // Load gadget
        this.createPlayer();
        final Gadget gadget = new GadgetImpl();
        Map<String, List<String>> gadgetMap = new HashMap<>();
        gadgetMap.put("Air Barry", List.of("true", "true", "100", "Moltiplicatore di salto iniziale"));
        gadgetMap.put("Gravity Belt", List.of("false", "true", "150", "Aumento gravita'"));
        GadgetImpl.setAll(gadgetMap);

        // Check if the player constuction was successful
        assertEquals(30, player.getCurrentPos().getX());
        assertEquals(250, player.getCurrentVel().getY());

        /*
         * First two test for the applyGadget() method, in first case the gadget
         * is not active, in the second case the gadget is active
         */
        player.setDirectionDOWN();
        assertEquals(160, player.getCurrentVel().getY());

        player.setDirectionUP();
        assertEquals(-188.5, player.getCurrentVel().getY());

        /* Set to active also the second gadget */
        gadget.setValue("Gravity Belt", "true", "true", "150$", "Aumento gravita'");

        player.setDirectionSTATIC();
        assertEquals(0, player.getCurrentVel().getY());

        /* Retry of the first testbut now with the gadget state active */
        player.setDirectionDOWN();
        assertEquals(208, player.getCurrentVel().getY());

    }
}
