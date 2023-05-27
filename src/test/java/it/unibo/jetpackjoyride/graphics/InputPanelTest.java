package it.unibo.jetpackjoyride.graphics;

import java.util.List;

import static org.junit.Assert.fail;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

import it.unibo.jetpackjoyride.graphics.impl.InputPanel;
import it.unibo.jetpackjoyride.input.api.Input;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputQueueImpl;

/**
 * JUnit test for the input panel.
 */
public class InputPanelTest {

    /**
     * Test if the input panel is correctly created.
     */
    @Test
    public void inputKeyTest() {
        final int dimension = 500;
        final JFrame frame = new JFrame();
        final JPanel panel = new JPanel();
        final InputQueue inputQueue = new InputQueueImpl();
        final InputPanel inputPanel = new InputPanel(inputQueue);
        frame.add(panel);
        frame.add(inputPanel);
        frame.setVisible(true);
        frame.setSize(dimension, dimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputPanel.requestFocus();
        inputPanel.keyPressed(new KeyEvent(inputPanel, 0, 0, 0, KeyEvent.VK_SPACE, ' '));
        final List<Input> inputList = inputQueue.getInputQueue();
        if (inputList.isEmpty()) {
            fail("Input list is empty");
        }
        for (final Input input : inputList) {
            switch (input.getType()) {
                case UP_PRESSED:
                    break;
                default:
                    fail("Wrong input type");
                    break;
            }
        }
        frame.dispose();
    }
}
