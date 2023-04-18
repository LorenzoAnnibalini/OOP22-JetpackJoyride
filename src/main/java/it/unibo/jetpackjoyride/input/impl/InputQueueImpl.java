package it.unibo.jetpackjoyride.input.impl;

import java.util.LinkedList;
import java.util.Objects;

import it.unibo.jetpackjoyride.input.api.Input;
import it.unibo.jetpackjoyride.input.api.InputQueue;

public class InputQueueImpl implements InputQueue {

    private LinkedList<Input> inputQueue;

    /**
     * Constructor for the input queue.
     */
    public InputQueueImpl() {
        this.inputQueue = new LinkedList<>();
    }

    @Override
    public void addInput(final Input input) {
        this.inputQueue.add(Objects.requireNonNull(input));
    }

    @Override
    public LinkedList<Input> getInputQueue() {
        return new LinkedList<>(this.inputQueue);
    }

    @Override
    public boolean isEmpty() {
        return this.inputQueue.isEmpty();
    }

}
