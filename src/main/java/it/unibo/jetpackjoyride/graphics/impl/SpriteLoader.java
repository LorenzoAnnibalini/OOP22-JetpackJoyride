package it.unibo.jetpackjoyride.graphics.impl;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;


/**
 * Class that loads from file and saves sprites' images.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public class SpriteLoader {
    private static final String ASSETS_FOLDER = "/assets/";
    private static Map<String, Sprite> sprites = new HashMap<>();

    /**
     * Getter of sprites.
     * 
     * @return the map of sprites
     */
    public Map<String, Sprite> getSprites() {
        return sprites;
    }

    /**
     * Method to get all sprites sclaed.
     * 
     * @return the map of scaled sprites
     */
    public Map<String, Sprite> getSpritesScaled() {
        final Map<String, Sprite> scaledSprites = new HashMap<>();
        for (final Map.Entry<String, Sprite> s : sprites.entrySet()) {
            s.getValue().scale();
            scaledSprites.put(s.getKey(), s.getValue());
        }
        return scaledSprites;
    }

    /**
     * Method to load sprites from file.
     * 
     * @param filename the name of the file
     * @throws ParseException exception if file is not parsed
     * @throws IOException exception if file is not found
     */
    public void loadSprites(final String filename) throws ParseException, IOException {
        final JSONParser parser = new JSONParser();
        String fileContent;
        JSONObject jsonObj = new JSONObject();
        //final InputStream stream = this.getClass().getResourceAsStream(filename);
        try (InputStream stream = this.getClass().getResourceAsStream(filename)){
            fileContent = new String(stream.readAllBytes(),
                    StandardCharsets.UTF_8);
            stream.close();
            jsonObj = (JSONObject) parser.parse(fileContent);
        } catch (ParseException | IOException e) {
            LogManager.getLogManager().getLogger(Logger.ROOT_LOGGER_NAME).severe(e.getMessage());
        }
        try {
            // load sprites
            final JSONArray jSprites = (JSONArray) jsonObj.get("sprites");
            for (final Object sprite : jSprites) {
                final JSONObject spriteObj = (JSONObject) sprite;
                final String name = spriteObj.get("name").toString();
                final String path = SpriteLoader.ASSETS_FOLDER + spriteObj.get("path").toString();
                final int width = ((Long) spriteObj.get("width")).intValue();
                final int height = ((Long) spriteObj.get("height")).intValue();
                final Image img = ImageIO.read(this.getClass().getResourceAsStream(path));
                sprites.put(name, new Sprite(width, height, img));
            }
            // load map
            final JSONArray jMap = (JSONArray) jsonObj.get("map");
            for (final Object object : jMap) {
                final JSONObject mapObj = (JSONObject) object;
                final String name = (String) mapObj.get("name");
                final String path = (String) mapObj.get("path");
                final int width = ((Long) mapObj.get("width")).intValue();
                final int height = ((Long) mapObj.get("height")).intValue();
                final Image img = ImageIO.read(this.getClass().getResourceAsStream(SpriteLoader.ASSETS_FOLDER + path));
                sprites.put(name, new Sprite(width, height, img));
            }

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
