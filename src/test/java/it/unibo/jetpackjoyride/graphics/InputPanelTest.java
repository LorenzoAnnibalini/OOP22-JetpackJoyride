package it.unibo.jetpackjoyride.graphics;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

import it.unibo.jetpackjoyride.graphics.impl.InputPanel;
import it.unibo.jetpackjoyride.input.api.Input;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.api.Input.TypeInput;
import it.unibo.jetpackjoyride.input.impl.InputQueueImpl;

public class InputPanelTest {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    InputQueue inputQueue = new InputQueueImpl();
    InputPanel inputPanel = new InputPanel(inputQueue);

    @Test
    public void inputKeyTest() {
        frame.add(panel);
        frame.add(inputPanel);
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputPanel.requestFocus();
        inputPanel.keyPressed(new KeyEvent(inputPanel, 0, 0, 0, KeyEvent.VK_SPACE, ' '));
        List<Input> inputList = new ArrayList<>();
        inputList = inputQueue.getInputQueue();
        if (inputList.isEmpty()) {
            assertTrue(false);
        }
        for (Input input : inputList) {
            switch (input.getType()) {
                case UP_PRESSED:
                    assertTrue(true);
                    break;
                default:
                    assertTrue(false);
                    break;
            }
        }
        frame.dispose();
    }
}
