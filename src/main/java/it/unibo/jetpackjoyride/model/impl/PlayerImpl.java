package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Player;


/**
 * This is a class to model a generic player.
 * @author mattia.burreli@studio.unibo.it
 */
public class PlayerImpl extends GameObject implements Player {
   
    private boolean statusPlayer;

    /**
     * constructor to create a player.
     * @param type
     * @param pos
     * @param vel
     */
    public PlayerImpl(Point2d pos, Vector2d vel) {
        super(Type.BARRY, pos, vel);
    }

    @Override
    public boolean getStatusPlayer() {
        return statusPlayer;
    }

    @Override
    public void setPlayerDeath() {
        this.statusPlayer = false;
    }

    @Override
    public void setPlayerAlive() {
        this.statusPlayer = true;
    }
    
}
