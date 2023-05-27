package it.unibo.jetpackjoyride.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.EntitiesGenerator;
import it.unibo.jetpackjoyride.model.impl.EntitiesGeneratorImpl;
import it.unibo.jetpackjoyride.model.impl.GameObject;

/**
 * JUnit test for {@link EntitiesGeneratorImpl}.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */

class TestGenerateObstacle {
    /**
     * Test for generateObstacles.
     * 
     * @throws InstantiationException    if the class cannot be instantiated
     * @throws IllegalAccessException    if the class or its nullary constructor is
     *                                   not accessible
     * @throws IllegalArgumentException  if the specified object is not an instance
     *                                   of the class or interface declaring the
     *                                   underlying constructor
     * @throws InvocationTargetException if the underlying constructor throws an
     *                                   exception
     * @throws NoSuchMethodException     if a matching method is not found
     * @throws SecurityException         if a security manager, s, is present and
     *                                   any of the following conditions is met:
     * @throws ClassNotFoundException    if the class cannot be found
     */
    @Test
    void testGenerateSomeEntity() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
        Set<Pair<String, GameObject>> entities = new HashSet<>();
        final EntitiesGenerator eg = new EntitiesGeneratorImpl();
        List<String> expectedReturns = List.of("Rocket", "Electrode", "ShieldPowerUp", "SpeedUpPowerUp", "Scientist",
                "Laser", "Nothing");
        // Test for generateObstacles
        eg.generateObstacles(entities, 1);
        assertEquals(1, entities.size());
        entities = eg.getEntities();
        // Test for generatePowerUps
        eg.generatePowerUps(entities, 2);
        assertEquals(3, entities.size());
        entities = eg.getEntities();
        // Test for generateScientists
        eg.generateScientists(entities, 4);
        assertEquals(7, entities.size());
        entities = eg.getEntities();
        for (final Pair<String, GameObject> ent : entities) {
            assertTrue(expectedReturns.contains(ent.getX()));
        }
    }
}
