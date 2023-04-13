package it.unibo.jetpackjoyride.model.impl;

import java.util.HashMap;
import java.util.Map;
import it.unibo.jetpackjoyride.model.api.Gadget;

/**
 * Class to modelize the Gadget information.
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class GadgetImpl implements Gadget{

    private Map<String, Boolean> Gadget = new HashMap<>();

    @Override
    public Map<String, Boolean> getAll() {
        return this.Gadget;
    }

    @Override
    public Boolean getValue(String name) {
        return this.Gadget.get(name);
    }

    @Override
    public void setValue(String name, Boolean state) {
        this.Gadget.replace(name, state);
    }
}
