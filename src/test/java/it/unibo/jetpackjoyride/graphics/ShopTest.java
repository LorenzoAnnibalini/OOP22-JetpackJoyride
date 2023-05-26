package it.unibo.jetpackjoyride.graphics;

import java.awt.Font;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import it.unibo.jetpackjoyride.core.api.GadgetLoader;
import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.core.api.SkinInfoLoader;
import it.unibo.jetpackjoyride.core.impl.GadgetLoaderImpl;
import it.unibo.jetpackjoyride.core.impl.SavesImpl;
import it.unibo.jetpackjoyride.core.impl.SkinInfoLoaderImpl;
import it.unibo.jetpackjoyride.graphics.impl.ShopPanel;
import it.unibo.jetpackjoyride.graphics.impl.SpriteLoader;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputQueueImpl;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;

/**
 * Class to test the GUI of the shop.
 * 
 * @author lorenzo.bacchini4@tudio.unibo.it
 */
public class ShopTest {

    public static void main(final String[] args) {
        final GadgetLoader gadgetLoader = new GadgetLoaderImpl();
        final SkinInfoLoader skinInfoLoader = new SkinInfoLoaderImpl();
        final SpriteLoader spriteLoader = new SpriteLoader();
        final Saves saves = new SavesImpl();
        final Statistics statistics = new StatisticsImpl();
        final Font customFont = new Font("Arial", Font.PLAIN, 48);
        final String fileName = "/config/sprites.json";
        try {
            spriteLoader.loadSprites(fileName);
        } catch (final Exception e) {
        }
        try {
            statistics.setAll(saves.downloadSaves());
        } catch (final Exception e) {
        }
        final InputQueue queue = new InputQueueImpl();
        try {
            gadgetLoader.downloadGadget();
        } catch (final FileNotFoundException e) {
        }
        try {
            skinInfoLoader.downloadSkin();
        } catch (final FileNotFoundException e) {
        }

        final JFrame frame = new JFrame();
        frame.setTitle("GUI shop test");
        frame.setSize(300, 400);
        frame.getContentPane().add(new ShopPanel(queue, statistics, customFont));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
