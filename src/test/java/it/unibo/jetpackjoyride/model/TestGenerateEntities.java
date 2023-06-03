package it.unibo.jetpackjoyride.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.EntitiesGenerator;
import it.unibo.jetpackjoyride.model.impl.EntitiesGeneratorImpl;
import it.unibo.jetpackjoyride.model.api.GameObject;

/**
 * JUnit test for {@link EntitiesGeneratorImpl}.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
class TestGenerateEntities {

    private static final int FIRSTSIZE = 1;
    private static final int SECONDSIZE = 3;
    private static final int THIRDSIZE = 7;

    /**
     * Test for generate entities.
     * This test generates a set of entities and checks if the number of entities
     * generated is correct and if the entities generated are correct.
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
    void testGenerateSomeEntity() {
        Set<Pair<String, GameObject>> entities = new HashSet<>();
        final EntitiesGenerator eg = new EntitiesGeneratorImpl();
        final List<String> expectedReturns = List.of("Rocket", "Electrode", "ShieldPowerUp", "SpeedUpPowerup",
                "Scientist",
                "Laser", "Nothing");
        // Test for generateObstacles
        eg.generateObstacles(entities, 1);
        entities = eg.getEntities();
        assertEquals(FIRSTSIZE, entities.size());
        // Test for generatePowerUps
        eg.generatePowerUps(entities, 2);
        entities = eg.getEntities();
        assertEquals(SECONDSIZE, entities.size());
        // Test for generateScientists
        eg.generateScientists(entities, 4);
        entities = eg.getEntities();
        assertEquals(THIRDSIZE, entities.size());
        for (final Pair<String, GameObject> ent : entities) {
            assertTrue(expectedReturns.contains(ent.getX()));
        }
    }
}
