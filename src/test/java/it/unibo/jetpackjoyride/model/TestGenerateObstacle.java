package it.unibo.jetpackjoyride.model;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.EntitiesGenerator;
import it.unibo.jetpackjoyride.model.impl.EntitiesGeneratorImpl;
import it.unibo.jetpackjoyride.model.impl.GameObject;

public class TestGenerateObstacle {
    @Test
    void testGenerateSomeEntity() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
        Set<Pair<String, GameObject>> baseSet = new HashSet<>();
        EntitiesGenerator eg = new EntitiesGeneratorImpl();
        eg.generateEntity(baseSet);
        baseSet = eg.getEntities();
        eg.generateEntity(baseSet);
        baseSet = eg.getEntities();
        eg.generateEntity(baseSet);
        baseSet = eg.getEntities();
        var result = eg.getEntities();
        for (Pair<String, GameObject> pair : result) {
            System.out.println(pair.getX() + " " + pair.getY());
        }
    }
}
