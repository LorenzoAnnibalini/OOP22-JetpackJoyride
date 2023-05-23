package it.unibo.jetpackjoyride.core.impl;

import it.unibo.jetpackjoyride.core.api.Slider;

/**
 * Class to do a thread agent to update and reset a value.
 * In this case the value is the position of the background.
 * 
 * @author emanuele.sanchi@studio.unibo.it
 */
public final class SliderImpl extends Thread implements Slider {

    private int pos = 0;
    private boolean stop = false;
    private final int limit;
    /**
     * Milliseconds to stop the thread.
     */
    private static final long STOPMILLIS = 25;

    /**
     * Constructor of thread agent.
     * 
     * @param limit value of max value
     */
    public SliderImpl(final int limit) {
        this.limit = limit;
    }

    @Override
    public void updatePos() {
        this.pos += 10;
    }

    @Override
    public void resetPos() {
        this.pos = 0;
    }

    @Override
    public synchronized int getPos() {
        return this.pos;
    }

    /**
     * Method to run the thread.
     */
    public void run() {
        while (!stop) {
            try {
                Thread.sleep(STOPMILLIS);
                if (this.pos < this.limit) {
                    this.updatePos();
                    // System.out.println(pos);
                } else {
                    this.resetPos();
                }
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to stop the thread.
     */
    public void interrupt() {
        this.stop = true;
    }
}
