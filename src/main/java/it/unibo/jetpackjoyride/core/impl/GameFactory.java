package it.unibo.jetpackjoyride.core.impl;

import it.unibo.jetpackjoyride.model.impl.*;
import it.unibo.jetpackjoyride.common.*;
import it.unibo.jetpackjoyride.model.impl.Electrode.Orientation;

public class GameFactory {
    
    static private GameFactory instance;

    static public GameFactory getInstance(){
        if(instance == null){
            instance = new GameFactory();
        }
        return instance;
    }

    public Electrode createElectrode(Point2d pos, Vector2d vel, Orientation orientation, double lenght, double width){
        return new Electrode(pos, vel, orientation, lenght, width);
    }

    public Rocket createRocket(Point2d pos, Vector2d vel, double lenght, double width){
        return new Rocket(pos, vel, lenght, width);
    }

    public LaserRay createLaserRay(Point2d pos, Vector2d vel, double lenght, double width){
        return new LaserRay(pos, vel, lenght, width);
    }
}
