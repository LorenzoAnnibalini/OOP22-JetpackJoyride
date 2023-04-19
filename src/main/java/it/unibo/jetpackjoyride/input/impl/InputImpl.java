package it.unibo.jetpackjoyride.input.impl;

import java.util.Objects;
import java.util.Optional;

import it.unibo.jetpackjoyride.input.api.Input;

/**
 * Class for the input.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public class InputImpl implements Input {

    private typeInput type;
    Optional<String> name;

    /**
     * Constructor for the input.
     * 
     * @param type the type of the input
     */
    public InputImpl(final typeInput type, final String name) {
        this.type = Objects.requireNonNull(type);
        this.name = Optional.ofNullable(name);
    }

    @Override
    public typeInput getType() {
        return this.type;
    }

    @Override
    public Optional<String> getName() {
        return this.name;
    }

}
