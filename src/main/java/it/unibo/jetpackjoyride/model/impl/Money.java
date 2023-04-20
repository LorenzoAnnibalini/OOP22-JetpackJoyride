package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * A class to model an in-game coin.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Money extends GameObject{
    
    public Money(final Point2d pos, final Vector2d vel,final Hitbox hitbox){
        super(pos, vel, hitbox);
    }
}
