package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.api.Scientist;

/**
 * TODO: Vedi commento su texture nell'interfaccia ?
 * Standard scientist implementation
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class ScientistImpl implements Scientist{

    final static double MAXSPEED = 10;

    private final Direction direction;
    private boolean life;
    private double speed;
    private String[] texture;
    private int textureIndex;
    private Point2d point;

    public ScientistImpl(Direction direction, String[] texture, Point2d point, double speed) {
        if(texture.length == 0 || point == null || direction == null) {
            throw new IllegalArgumentException("Input can't be empty");
        }else{
            this.direction = direction;
            this.life = true;
            this.setTextureArray(texture);
            this.point = point;
            this.setSpeed(speed);
        }
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public Boolean isAlive() {
        return this.life;
    }

    @Override
    public void killScientist() {
        this.life = false;    
    }

    @Override
    public void setSpeed(double speed) {
        if(speed < 0 && speed > MAXSPEED) {
            throw new IllegalArgumentException("Speed can't be negative");
        }
        this.speed = speed;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public String[] getTextureArray() {
        return this.texture;
    }

    @Override
    public String getCurrentTexture() {
        return this.texture[this.textureIndex];
    }

    @Override
    public void setTextureArray(String[] texture) {
        if(texture.length == 0) {
            throw new IllegalArgumentException("Texture can't be empty");
        }else{
            this.texture = texture;
        }
    }

    @Override
    public String run() {
        if(this.textureIndex == this.texture.length) {
            this.textureIndex = 0;
        }
        this.nextPosition();
        return this.texture[this.textureIndex++];
    }

    private void nextPosition(){
        //TODO: Come vogliamo gestire la velocità ? 
        //chiamiamo run per ogni frame e usiamo la velocità per aggiornare la posizione ogni x frame ?
        //usiamo la velocità per sommare la posizione e chiamamo run ogni x frame?
        if(direction == Direction.LEFT)
            this.point.x=(this.point.x - this.getSpeed());
        else if(direction == Direction.RIGHT){
            this.point.x=(this.point.x + this.getSpeed());
        }
    }    
}
