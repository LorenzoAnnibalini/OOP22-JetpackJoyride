package it.unibo.jetpackjoyride.graphics.impl;

import it.unibo.jetpackjoyride.common.Pair;

import java.awt.Image;

/**
 * A class to modelize a sprite.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class Sprite {
    private Pair<Integer, Integer> originalDimension;
    private Pair<Integer, Integer> scaledDimension;
    private Image originalImage;
    private Image scaledImage;

    /**
     * Constructor of the class.
     * 
     * @param width  width of the sprite
     * @param height height of the sprite
     * @param img    image of the sprite
     */
    public Sprite(int width, int height, Image img) {
        this.originalDimension = new Pair<>(width, height);
        this.scaledDimension = this.originalDimension;
        this.originalImage = img;
        this.scaledImage = this.originalImage;
    }

    /**
     * Method to scale the sprite.
     * 
     * @param x x scale
     * @param y y scale
     */
    public void scale(double x, double y) {
        this.scaledDimension = new Pair<>((int) (originalDimension.getX() * x), (int) (originalDimension.getY() * y));
        this.scaledImage = originalImage.getScaledInstance(this.scaledDimension.getX(), this.scaledDimension.getY(),
                Image.SCALE_SMOOTH);
    }

    /**
     * Getter of original image
     * 
     * @return the image with original dimension
     */
    public Image getOriginal() {
        return this.originalImage;
    }

    /**
     * Getter of original dimension
     * 
     * @return a pair with original dimension
     */
    public Pair<Integer, Integer> getOriginalDim() {
        return this.originalDimension;
    }

    /**
     * Getter of scaked image
     * 
     * @return the image with scaled dimension
     */
    public Image getScaled() {
        return this.scaledImage;
    }

    /**
     * Getter of scaled dimension
     * 
     * @return a pair with scaled dimension
     */
    public Pair<Integer, Integer> getScaledlDim() {
        return this.scaledDimension;
    }
}
