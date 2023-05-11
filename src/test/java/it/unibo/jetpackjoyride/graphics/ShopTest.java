package it.unibo.jetpackjoyride.graphics;

import java.io.FileNotFoundException;

import javax.swing.JFrame;

import it.unibo.jetpackjoyride.core.api.GadgetLoader;
import it.unibo.jetpackjoyride.core.api.SkinInfoLoader;
import it.unibo.jetpackjoyride.core.impl.GadgetLoaderImpl;
import it.unibo.jetpackjoyride.core.impl.SkinInfoLoaderImpl;
import it.unibo.jetpackjoyride.graphics.impl.ShopPanel;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputQueueImpl;

/**
 * Class to test the GUI of the shop.
 * 
 * @author lorenzo.bacchini4@tudio.unibo.it
 */
public class ShopTest {
    
    public static void main(String[] args) {
        GadgetLoader gadgetLoader = new GadgetLoaderImpl();
        SkinInfoLoader skinInfoLoader = new SkinInfoLoaderImpl();
        InputQueue queue = new InputQueueImpl();
        try{
            gadgetLoader.downloadGadget();
        }catch(FileNotFoundException e){}
        try{
            skinInfoLoader.downloadSkin();
        }catch(FileNotFoundException e){}
        
        JFrame frame = new JFrame();
        frame.setTitle("GUI shop test");
        frame.setSize(300, 400);
        frame.getContentPane().add(new ShopPanel(queue));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
