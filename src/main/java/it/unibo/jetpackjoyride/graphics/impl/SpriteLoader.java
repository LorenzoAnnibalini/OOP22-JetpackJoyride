package it.unibo.jetpackjoyride.graphics.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that loads from file and saves sprites' images.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class SpriteLoader {
    // sprites files names
    private final static String ROCKET = "rocket";
    private final static String ELECTRODE = "electrode";
    private final static String SHIELD = "shield";
    private final static String SPEEDUP = "speedup";
    private final static String SCIENTIST = "scientist";
    private final static String LASER = "laser";
    private final static String PLAYER = "player";
    private final static String MONEY = "money";

    private Map<String, Sprite> sprites = new HashMap<>();

    /**
     * Getter of sprites.
     * @return the map of sprites
     */
    public Map<String, Sprite> getSprites() {
        return sprites;
    }

    /**
     * Method to load sprites from file.
     * 
     * @param filename the name of the file
     */
    public void loadSprites(String filename) {
        // TODO
    }
}
