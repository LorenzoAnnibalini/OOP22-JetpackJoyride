package it.unibo.jetpackjoyride.core.impl;

import it.unibo.jetpackjoyride.model.api.Hitbox;
import it.unibo.jetpackjoyride.model.impl.*;
import it.unibo.jetpackjoyride.common.*;
import it.unibo.jetpackjoyride.model.api.Orientation;

public class GameFactory {
    
    static private GameFactory instance;

    static public GameFactory getInstance(){
        if(instance == null){
            instance = new GameFactory();
        }
        return instance;
    }

    public Electrode createElectrode(Point2d pos, Vector2d vel, Orientation orientation, Hitbox hitbox){
        return new Electrode(pos, vel, orientation, hitbox);
    }

    public Rocket createRocket(Point2d pos, Vector2d vel,Hitbox hitbox){
        return new Rocket(pos, vel, hitbox);
    }

    public LaserRay createLaserRay(Point2d pos, Vector2d vel,Hitbox hitbox){
        return new LaserRay(pos, vel,hitbox);
    }
}
