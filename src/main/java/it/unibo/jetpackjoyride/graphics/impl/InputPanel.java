package it.unibo.jetpackjoyride.graphics.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import it.unibo.jetpackjoyride.input.api.Input;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputImpl;

/**
 * This is a class to model a generic input panel.
 * This class is used to manage the input of the user.
 * It implements the KeyListener interface.
 * 
 * @see KeyListener
 * @see JPanel
 * @see InputQueue
 * @see InputImpl
 * @see Input
 * @see Input.typeInput
 * @see InputPanel
 * 
 * @author mattia.burreli@studio.unibo.it
 */
public class InputPanel extends JPanel implements KeyListener {

    private InputQueue inputHandler;
    private boolean isPressed;

    public InputPanel(InputQueue inputHandler) {
        this.isPressed = false;
        this.inputHandler = inputHandler;
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setSize(0, 0);
        this.setVisible(true);
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == 32 && this.isPressed != true) {
            this.isPressed = true;
            this.inputHandler.addInput(new InputImpl(Input.typeInput.UP_PRESSED, "UP_PRESSED"));
        }
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() == 32 && this.isPressed != false) {
            this.isPressed = false;
            this.inputHandler.addInput(new InputImpl(Input.typeInput.UP_RELEASED, "UP_RELEASED"));
        }
    }
}
