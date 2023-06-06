package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * A class to model an in-game money.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Money extends GameObjectImpl {

    /**
     * Cosntructor of the class Money.
     * @param pos position
     * @param vel velocity
     * @param hitbox hitbox
     */
    public Money(final Point2d pos, final Vector2d vel, final Hitbox hitbox) {
        super(pos, vel, hitbox);
    }

}
