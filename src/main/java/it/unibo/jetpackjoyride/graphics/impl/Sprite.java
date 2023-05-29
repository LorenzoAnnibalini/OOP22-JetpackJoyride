package it.unibo.jetpackjoyride.graphics.impl;

import it.unibo.jetpackjoyride.common.Pair;

import java.awt.Image;
import java.awt.Toolkit;


/**
 * A class to modelize a sprite.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class Sprite {
    private final Pair<Integer, Integer> originalDimension;
    private final Pair<Integer, Integer> scaledDimension;
    private final Image originalImage;
    private Image scaledImage;

    /**
     * Constructor of the class.
     * 
     * @param width  width of the sprite
     * @param height height of the sprite
     * @param img    image of the sprite
     */
    public Sprite(final int width, final int height, final Image img) {
        this.originalDimension = new Pair<>(img.getWidth(null), img.getHeight(null));
        this.scaledDimension = new Pair<>(width, height);
        this.originalImage = Toolkit.getDefaultToolkit().createImage(img.getSource());
    }

    /**
     * Method to scale the sprite.
     *
     */
    public void scale() {
        this.scaledImage = originalImage.getScaledInstance(this.scaledDimension.getX(), this.scaledDimension.getY(),
                Image.SCALE_SMOOTH);
    }

    /**
     * Getter of original image.
     * 
     * @return the image with original dimension
     */
    public Image getOriginal() {
        return Toolkit.getDefaultToolkit().createImage(this.originalImage.getSource());
    }

    /**
     * Getter of original dimension.
     * 
     * @return a pair with original dimension
     */
    public Pair<Integer, Integer> getOriginalDim() {
        return this.originalDimension;
    }

    /**
     * Getter of scaked image.
     * 
     * @return the image with scaled dimension
     */
    public Image getScaled() {
        return Toolkit.getDefaultToolkit().createImage(this.scaledImage.getSource());
    }

    /**
     * Getter of scaled dimension.
     * 
     * @return a pair with scaled dimension
     */
    public Pair<Integer, Integer> getScaledlDim() {
        return this.scaledDimension;
    }
}
