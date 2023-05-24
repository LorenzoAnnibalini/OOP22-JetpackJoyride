package it.unibo.jetpackjoyride.input.impl;

import java.util.Objects;
import java.util.Optional;

import it.unibo.jetpackjoyride.input.api.Input;

/**
 * Class for the input.
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public final class InputImpl implements Input {

    private TypeInput type;
    private Optional<String> name;

    /**
     * Constructor for the input.
     * 
     * @param type the type of the input
     * @param name the name of the input
     */
    public InputImpl(final TypeInput type, final String name) {
        this.type = Objects.requireNonNull(type);
        this.name = Optional.ofNullable(name);
    }

    @Override
    public TypeInput getType() {
        return this.type;
    }

    @Override
    public Optional<String> getName() {
        return this.name;
    }

}
