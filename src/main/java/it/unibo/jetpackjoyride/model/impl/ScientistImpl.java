package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.api.Scientist;

public class ScientistImpl implements Scientist{

    private final Direction direction;
    private boolean life;

    public ScientistImpl(Direction direction) {
        this.direction = direction;
        this.life = true;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public Boolean isAlive() {
        return this.life;
    }

    @Override
    public void killScientist() {
        this.life = false;    
    }
    
}
