package it.unibo.jetpackjoyride.graphics.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Class that loads from file and saves sprites' images.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class SpriteLoader {
    // sprites files names
    /*
     * private final static String ROCKET = "rocket";
     * private final static String ELECTRODE = "electrode";
     * private final static String SHIELD = "shield";
     * private final static String SPEEDUP = "speedup";
     * private final static String SCIENTIST = "scientist";
     * private final static String LASER = "laser";
     * private final static String PLAYER = "player";
     * private final static String MONEY = "money";
     */
    private static final String FILESEPARATOR = File.separator;
    private static final int SPRITEWIDTH = 30;
    private static final int SPRITEHEIGHT = 30;

    private Map<String, Sprite> sprites = new HashMap<>();

    /**
     * Getter of sprites.
     * 
     * @return the map of sprites
     */
    public Map<String, Sprite> getSprites() {
        return sprites;
    }

    /**
     * Method to load sprites from file.
     * 
     * @param filename the name of the file
     * @throws ParseException
     */
    public void loadSprites(String filename) throws ParseException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray jSprites = (JSONArray) jsonObj.get("sprites");
            for (Object sprite : jSprites) {
                JSONObject spriteObj = (JSONObject) sprite;
                String name = (String) spriteObj.get("name");
                String path = (String) spriteObj.get("path");
                int width = ((Long) spriteObj.get("width")).intValue();
                int height = ((Long) spriteObj.get("height")).intValue();
                // Image img = ImageIO.read(null)
                BufferedImage originalImage = ImageIO
                        .read(new File("resources" + SpriteLoader.FILESEPARATOR + path));
                Image img = originalImage.getScaledInstance(SpriteLoader.SPRITEWIDTH, SpriteLoader.SPRITEHEIGHT,
                        Image.SCALE_SMOOTH);
                this.sprites.put(name, new Sprite(width, height, img));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
