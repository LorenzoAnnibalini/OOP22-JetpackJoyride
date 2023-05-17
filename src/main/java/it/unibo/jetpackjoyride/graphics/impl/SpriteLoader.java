package it.unibo.jetpackjoyride.graphics.impl;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
    private static final String ASSETS_FOLDER = "/assets/";
    private Map<String, Sprite> sprites = new HashMap<>();

    /**
     * Getter of sprites.
     * 
     * @return the map of sprites
     */
    public Map<String, Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Method to get all sprites sclaed
     */
    public Map<String, Sprite> getSpritesScaled() {
        Map<String, Sprite> scaledSprites = new HashMap<>();
        for (Map.Entry<String, Sprite> s : this.sprites.entrySet()) {
            s.getValue().scale();
            scaledSprites.put(s.getKey(), s.getValue());
        }
        return scaledSprites;
    }

    /**
     * Method to load sprites from file.
     * 
     * @param filename the name of the file
     * @throws ParseException
     */
    public void loadSprites(String filename) throws ParseException {
        final JSONParser parser = new JSONParser();
        String fileContent;
        JSONObject jsonObj = new JSONObject();

        try {
            final InputStream stream = this.getClass().getResourceAsStream(filename);
            fileContent = new String(stream.readAllBytes(),
                    StandardCharsets.UTF_8);
            stream.close();
            jsonObj = (JSONObject) parser.parse(fileContent);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        try {
            // load sprites
            JSONArray jSprites = (JSONArray) jsonObj.get("sprites");
            for (Object sprite : jSprites) {
                JSONObject spriteObj = (JSONObject) sprite;
                String name = spriteObj.get("name").toString();
                String path = SpriteLoader.ASSETS_FOLDER + spriteObj.get("path").toString();
                int width = ((Long) spriteObj.get("width")).intValue();
                int height = ((Long) spriteObj.get("height")).intValue();
                Image img = ImageIO.read(this.getClass().getResourceAsStream(path));
                this.sprites.put(name, new Sprite(width, height, img));
            }
            // load map
            JSONArray jMap = (JSONArray) jsonObj.get("map");
            for (Object object : jMap) {
                JSONObject mapObj = (JSONObject) object;
                String name = (String) mapObj.get("name");
                String path = (String) mapObj.get("path");
                int width = ((Long) mapObj.get("width")).intValue();
                int height = ((Long) mapObj.get("height")).intValue();
                Image img = ImageIO.read(this.getClass().getResourceAsStream(SpriteLoader.ASSETS_FOLDER + path));
                this.sprites.put(name, new Sprite(width, height, img));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
