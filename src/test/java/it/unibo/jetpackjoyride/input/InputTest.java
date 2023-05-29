package it.unibo.jetpackjoyride.input;

import static org.junit.Assert.assertEquals;

import java.util.List;

import it.unibo.jetpackjoyride.input.api.Input;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.input.impl.InputQueueImpl;

/**
 * This is a class to test the input queue.
 */
public class InputTest {

    /**
     * This is a test to check if the input queue is created and if the input are added.
     */
    public void testInput() {

        final InputQueue inputQueue = new InputQueueImpl();
        final int dimension = 5;
        inputQueue.addInput(new InputImpl(Input.TypeInput.MENU, "menu"));
        inputQueue.addInput(new InputImpl(Input.TypeInput.UP_PRESSED, "up"));
        inputQueue.addInput(new InputImpl(Input.TypeInput.UP_RELEASED, "endUp"));
        inputQueue.addInput(new InputImpl(Input.TypeInput.EXIT, "exit"));
        inputQueue.addInput(new InputImpl(Input.TypeInput.SHOP, "shop"));
        final List<Input> inputList = inputQueue.getInputQueue();
        assertEquals(dimension, inputList.size());
        assertEquals(true, inputQueue.isEmpty());
    }

}
