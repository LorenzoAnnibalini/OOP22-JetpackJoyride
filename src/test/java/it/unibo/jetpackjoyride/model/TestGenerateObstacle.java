package it.unibo.jetpackjoyride.model;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;

import it.unibo.jetpackjoyride.common.Pair;
import it.unibo.jetpackjoyride.model.api.EntitiesGenerator;
import it.unibo.jetpackjoyride.model.impl.EntitiesGeneratorImpl;
import it.unibo.jetpackjoyride.model.impl.GameObject;

public class TestGenerateObstacle {
    @Test
    void testGenerateSomeEntity() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
        //Rocket r = new Rocket(null, null, null);
        //Electrode e = new Electrode(null, null, null, null);
        EntitiesGenerator eg = new EntitiesGeneratorImpl();
        eg.generateEntity();
        eg.generateEntity();
        eg.generateEntity();
        var result = eg.getEntities();
        for (Pair<String,GameObject> pair : result) {
            System.out.println(pair.getX() + " " + pair.getY());
        }
    }
}
