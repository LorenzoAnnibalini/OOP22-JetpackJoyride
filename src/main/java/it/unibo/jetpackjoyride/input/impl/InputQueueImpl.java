package it.unibo.jetpackjoyride.input.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.unibo.jetpackjoyride.input.api.Input;
import it.unibo.jetpackjoyride.input.api.InputQueue;

/**
 * Implementation of the input queue.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public final class InputQueueImpl implements InputQueue {

    private final List<Input> inputQueue;

    /**
     * Constructor for the input queue.
     */
    public InputQueueImpl() {
        this.inputQueue = new ArrayList<>();
    }

    @Override
    public void addInput(final Input input) {
        this.inputQueue.add(Objects.requireNonNull(input));
    }

    @Override
    public List<Input> getInputQueue() {
        final List<Input> inputQueueCopy = new ArrayList<>(this.inputQueue);
        this.inputQueue.clear();
        return inputQueueCopy;
    }

    @Override
    public boolean isEmpty() {
        return this.inputQueue.isEmpty();
    }

}
