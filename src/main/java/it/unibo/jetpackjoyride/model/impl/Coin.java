package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;

/**
 * A class to model an in-game coin.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Coin extends GameObject{

    public Coin(Type type, Point2d pos, Vector2d vel) {
        super(type, pos, vel);
    }
}
