package it.unibo.jetpackjoyride.model;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
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

public class TestGenerateObstacle {
    @Test
    void testGenerateSomeEntity() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
        Set<Pair<String, GameObject>> baseSet = new HashSet<>();
        EntitiesGenerator eg = new EntitiesGeneratorImpl();
        eg.generateObstacles(baseSet, 1);
        baseSet = eg.getEntities();
        eg.generatePowerUps(baseSet, 1);
        baseSet = eg.getEntities();
        eg.generateScientists(baseSet, 1);
        baseSet = eg.getEntities();
        var result = eg.getEntities();
        for (Pair<String, GameObject> pair : result) {
            System.out.println(pair.getX() + " " + pair.getY());
        }
    }
}
