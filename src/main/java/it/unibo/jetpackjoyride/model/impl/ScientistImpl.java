package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.common.Point2d;
import it.unibo.jetpackjoyride.common.Vector2d;
import it.unibo.jetpackjoyride.model.api.Direction;
import it.unibo.jetpackjoyride.model.api.Scientist;

/**
 * TODO: Vedi commento su texture nell'interfaccia ?
 * Standard scientist implementation
 * @author lorenzo.annibalini@studio.unibo.it
 */

public class ScientistImpl extends GameObject implements Scientist{

    private final Direction direction;
    private boolean life;
    private String[] texture;
    private int textureIndex;

    public ScientistImpl(Direction direction, String[] texture, Point2d point, Vector2d velocity) {
        super(Type.SCIENTIST, point, velocity);
        if(texture.length == 0 || point == null || direction == null) {
            throw new IllegalArgumentException("Input can't be empty");
        }else{
            this.direction = direction;
            this.life = true;
            this.setTextureArray(texture);
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
    public Point2d getCurrentPos(){
        return super.getCurrentPos();
    }

        @Override
    public void setCurrentPos(Point2d point) {
        if(point == null) {
            throw new IllegalArgumentException("Point can't be null");
        }else{ 
            super.setPos(point);
        } 
    }

    @Override
    public Vector2d getVelocity() {
        return super.getCurrentVel();
    }

    @Override
    public void setVelocity(Vector2d velocity) {
        if(velocity == null) {
            throw new IllegalArgumentException("Velocity can't be null");
        }else{ 
            super.setVel(velocity);
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
            super.setPos(super.getCurrentPos().sub(super.getCurrentVel()));
        else if(direction == Direction.RIGHT){
            super.setPos(super.getCurrentPos().sum(super.getCurrentVel()));
        }
    }
}
