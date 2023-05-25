package it.unibo.jetpackjoyride.input.api;

import java.util.List;

/**
 * Interface for the input queue.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public interface InputQueue {

    /**
     * Add an input to the queue.
     * 
     * @param input the input to add
     */
    void addInput(Input input);

    /**
     * Get a copy of imput queue.
     * 
     * @return a list of inputs.
     */
    List<Input> getInputQueue();

    /**
     * Check if the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise
     */
    boolean isEmpty();

}
