package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.api.Hitbox;
import it.unibo.jetpackjoyride.model.api.Scientist;

/**
 * Standard scientist implementation
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class ScientistImpl extends GameObject implements Scientist {

    private final Direction direction;
    private boolean life;

    public ScientistImpl(final Direction direction, final Point2d point, final Vector2d velocity, final Hitbox hitbox) {
        super(point, velocity, hitbox);
        if (direction == null) {
            throw new IllegalArgumentException("Input can't be empty");
        } else {
            this.direction = direction;
            this.life = true;
        }
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

    @Override
   public void nextPosition() {
        if (direction == Direction.LEFT)
            super.setPos(super.getCurrentPos().sub(super.getCurrentVel()));
        else if (direction == Direction.RIGHT){
            super.setPos(super.getCurrentPos().sum(super.getCurrentVel()));
        }
    }
}
