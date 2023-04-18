package it.unibo.jetpackjoyride.input.impl;

import java.util.Objects;

import it.unibo.jetpackjoyride.input.api.Input;

public class InputImpl implements Input {

    private typeInput type;

    /**
     * Constructor for the input.
     * 
     * @param type the type of the input
     */
    public InputImpl(final typeInput type) {
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public typeInput getType() {
        return this.type;
    }

}
