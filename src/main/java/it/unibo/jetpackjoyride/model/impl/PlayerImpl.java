package it.unibo.jetpackjoyride.model.impl;

import java.util.List;
import java.util.Map;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Gadget;
import it.unibo.jetpackjoyride.model.api.Hitbox;
import it.unibo.jetpackjoyride.model.api.Player;
import it.unibo.jetpackjoyride.core.api.GadgetInfoPositions;

/**
 * This is a class to model a generic player.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public final class PlayerImpl extends GameObject implements Player {

    private boolean statusPlayer;
    private int hearts;
    private PlayerDirection direction;
    private final StatisticsImpl statistics;
    private static final int UP_VELOCITY = 145;
    private static final int DOWN_VELOCITY = -160;
    private static final double AIR_BARRY_MULTIPLIER = 1.3;
    private static final double GRAVITY_BELT_MULTIPLIER = 1.3;
    private static final String SUPPVALUE1 = "EI_EXPOSE_REP2";
    private static final String SUPPVALUE2 = "EI_EXPOSE_REP";
    private static final String SUPPJUSTIFICATION1 = "Statistics are meant to be the same for the player and the world";

    /**
     * constructor to create a player.
     * 
     * @param pos
     * @param vel
     * @param hitbox
     * @param statistics
     */
    @SuppressFBWarnings(value = SUPPVALUE1, justification = SUPPJUSTIFICATION1)
    public PlayerImpl(final Point2d pos, final Vector2d vel, final Hitbox hitbox, final StatisticsImpl statistics) {
        super(pos, vel, hitbox);
        this.hearts = 1;
        this.setPlayerAlive();
        this.direction = PlayerDirection.STATIC;
        this.statistics = statistics;
    }

    @Override
    public boolean isStatusPlayer() {
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
        if (this.hearts < 2) {
            this.hearts++;
        }
    }

    @Override
    public void removeHeart() {
        if (this.hearts > 1) {
            this.hearts--;
        } else {
            this.hearts--;
            this.setPlayerDeath();
        }

    }

    @Override
    public void setDirectionUP() {
        this.direction = PlayerDirection.UP;
        final double multiplier = this.applyGadget(direction);
        this.setVel(new Vector2d(this.getCurrentPos(),
                new Point2d(this.getCurrentPos().getX(), this.getCurrentPos().getY() + UP_VELOCITY * multiplier)));
    }

    @Override
    public void setDirectionDOWN() {
        this.direction = PlayerDirection.DOWN;
        final double multiplier = this.applyGadget(direction);
        this.setVel(new Vector2d(this.getCurrentPos(),
                new Point2d(this.getCurrentPos().getX(), this.getCurrentPos().getY() + DOWN_VELOCITY * multiplier)));
    }

    @Override
    public void setDirectionSTATIC() {
        this.direction = PlayerDirection.STATIC;
        this.setVel(new Vector2d(this.getCurrentPos(), this.getCurrentPos()));
    }

    @Override
    public PlayerDirection getDirection() {
        return this.direction;
    }

    /**
     * Method to apply gadget effects to the player.
     * 
     * @param direction
     * @return a multiplier to apply to the velocity
     */
    private double applyGadget(final PlayerDirection direction) {
        final Gadget gadget = new GadgetImpl();
        final Map<String, List<String>> gadgets = gadget.getAll();
        for (final Map.Entry<String, List<String>> entry : gadgets.entrySet()) {
            if ("true".equals(gadgets.get(entry.getKey()).get(GadgetInfoPositions.STATE.ordinal()))) {
                switch (entry.getKey()) {
                    case "Air Barry":
                        if (direction == PlayerDirection.UP) {
                            return AIR_BARRY_MULTIPLIER;
                        }
                        break;
                    case "Gravity Belt":
                        if (direction == PlayerDirection.DOWN) {
                            return GRAVITY_BELT_MULTIPLIER;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return 1;
    }

    @Override
    public int getHearts() {
        return this.hearts;
    }

    @Override
    @SuppressFBWarnings(value = SUPPVALUE2, justification = SUPPJUSTIFICATION1)
    public StatisticsImpl getStatistics() {
        return this.statistics;
    }
}
