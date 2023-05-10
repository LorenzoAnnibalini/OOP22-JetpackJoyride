package it.unibo.jetpackjoyride.core.impl;

import it.unibo.jetpackjoyride.core.api.Slider;

public class SliderImpl extends Thread implements Slider {

    private int pos = 0;
    private boolean stop = false;
    private int limit;

    /**
     * Constructor of thread agent.
     * 
     * @param limit value of max value
     */
    public SliderImpl(int limit) {
        this.limit = limit;
    }

    @Override
    public void updatePos() {
        this.pos++;
    }

    @Override
    public void resetPos() {
        this.pos = 0;
    }

    @Override
    public synchronized int getPos() {
        return this.pos;
    }

    public void run() {
        while (!stop) {
            try {
                Thread.sleep(5);
                if (this.pos < this.limit) {
                    this.updatePos();
                } else {
                    this.resetPos();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void interrupt() {
        this.stop = true;
    }
}
