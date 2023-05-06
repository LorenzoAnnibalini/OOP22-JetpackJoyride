package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;
import it.unibo.jetpackjoyride.model.api.Player;

/**
 * This is a class to model a generic player.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public class PlayerImpl extends GameObject implements Player {

    private boolean statusPlayer;
    private int hearts;
    private PlayerDirection direction;

    /**
     * constructor to create a player.
     * 
     * @param pos
     * @param vel
     */
    public PlayerImpl(Point2d pos, Vector2d vel, Hitbox hitbox) {
        super(pos, vel, hitbox);
        this.hearts = 1;
        this.setPlayerAlive();
        this.direction = PlayerDirection.STATIC;
    }

    @Override
    public boolean getStatusPlayer() {
        return statusPlayer;
    }

    /**
     * Function for set the status of player to false.
     */
    private void setPlayerDeath() {
        this.statusPlayer = false;
    }

    /**
     * Function for set the status of player to true.
     */
    private void setPlayerAlive() {
        this.statusPlayer = true;
    }

    @Override
    public void addHeart() {
        this.hearts++;
    }

    @Override
    public void removeHeart() {
        if (this.hearts > 0) {
            this.hearts--;
        } else {
            this.setPlayerDeath();
        }

    }

    @Override
    public void setDirectionUP() {
        this.direction = PlayerDirection.UP;
    }

    @Override
    public void setDirectionDOWN() {
        this.direction = PlayerDirection.DOWN;
    }

    @Override
    public void setDirectionSTATIC() {
        this.direction = PlayerDirection.STATIC;
    }

    @Override
    public PlayerDirection getDirection() {
        return this.direction;
    }

}
