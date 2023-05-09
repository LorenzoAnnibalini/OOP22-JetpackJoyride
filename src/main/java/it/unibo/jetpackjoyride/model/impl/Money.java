package it.unibo.jetpackjoyride.model.impl;

import java.util.ArrayList;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Hitbox;

/**
 * A class to model an in-game money.
 * @author lorenzo.bacchini4@studio.unibo.it
 */

public class Money extends GameObject{
    
    public Money(final Point2d pos, final Vector2d vel,final Hitbox hitbox){
        super(pos, vel, hitbox);
    }

    public static ArrayList<Money> getMoneyFromStringList(final ArrayList<String> moneyString){
        final int X = 0;
        final int Y = 1;
        ArrayList<Money> moneyList = new ArrayList<>();
        moneyString.stream().map(e -> e.split(",")).forEach(e -> {
            int x =  Integer.parseInt(e[X]);
            int y =  Integer.parseInt(e[Y]);
            Point2d pos = new Point2d(x, y);
            Vector2d vec = new Vector2d(0, y);
            Hitbox hitbox = new HitboxImpl(5, 5, pos);
            moneyList.add(new Money(pos, vec, hitbox));
        });
        return moneyList;
    }
}
