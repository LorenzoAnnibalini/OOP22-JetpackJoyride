package it.unibo.jetpackjoyride.model.impl;

import java.util.List;
import java.util.ArrayList;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * A class to model an in-game money.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Money extends GameObject {

    private static final int X = 0;
    private static final int Y = 1;

    /**
     * Cosntructor of the class Money.
     * @param pos position
     * @param vel velocity
     * @param hitbox hitbox
     */
    public Money(final Point2d pos, final Vector2d vel, final Hitbox hitbox) {
        super(pos, vel, hitbox);
    }

    /**
     * Static method to create a list of money from a list of string.
     * 
     * @param moneyString the list of string to convert
     * @return moneyList, the list of money
     */
    public static List<Money> getMoneyFromStringList(final List<String> moneyString) {
        final ArrayList<Money> moneyList = new ArrayList<>();
        moneyString.stream().map(e -> e.split(",")).forEach(e -> {
            final int x = Integer.parseInt(e[X]);
            final int y = Integer.parseInt(e[Y]);
            final Point2d pos = new Point2d(x, y);
            final Vector2d vec = new Vector2d(0, y);
            final Hitbox hitbox = new HitboxImpl(5, 5, pos);
            moneyList.add(new Money(pos, vec, hitbox));
        });
        return moneyList;
    }
}
