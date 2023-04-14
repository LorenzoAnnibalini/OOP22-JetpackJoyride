package it.unibo.jetpackjoyride.core.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.jetpackjoyride.core.api.EntitiesGeneration;
import it.unibo.jetpackjoyride.model.api.Gadget;
import it.unibo.jetpackjoyride.model.api.PowerUp;
import it.unibo.jetpackjoyride.model.impl.AutomaticPowerUp;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;

public class EntitiesGenerationImpl implements EntitiesGeneration {

    private Set<PowerUp> gadgets = new HashSet<>();

    @Override
    public void generateObstacle() {
        this.gadgets.add(new AutomaticPowerUp(null, 0, 0));
    }

    @Override
    public void powerUp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'powerUp'");
    }

    @Override
    public void generateScientist() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateScientist'");
    }
    
}
