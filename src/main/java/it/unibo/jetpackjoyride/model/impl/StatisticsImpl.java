package it.unibo.jetpackjoyride.model.impl;

import it.unibo.jetpackjoyride.model.api.Statistics;

public class StatisticsImpl implements Statistics{

    private int deaths;
    private int killedNpc;
    private int grabbedMoney;
    private int maxMoney;
    private int moneySpent;
    private int maxMeters;
    private int totalMeters;
    private int grabbedObjects;

    /**
     * Constructor of class Statistics. This constructor will be called by the core during the loading of the game with the information in the csv file
     * @param deaths number of deaths
     * @param killedNpc number of killed npc
     * @param grabbedMoney number of grabbed money
     * @param maxMoney number of max grabbed money
     * @param moneySpent number of money spent
     * @param maxMeters number of max meters performed
     * @param totalMeters number of total meters performed
     * @param grabbedObjects number of total gabbed objects
     */
    public StatisticsImpl(int deaths, int killedNpc, int grabbedMoney, int maxMoney, int moneySpent, int maxMeters, int totalMeters, int grabbedObjects) {
        this.deaths = deaths;
        this.killedNpc = killedNpc;
        this.grabbedMoney = grabbedMoney;
        this.maxMoney = maxMoney;
        this.moneySpent = moneySpent;
        this. maxMeters = maxMeters;
        this.totalMeters = totalMeters;
        this.grabbedObjects = grabbedObjects;
    }

    @Override
    public int getDeaths() {
        return this.deaths;
    }

    @Override
    public void incrementDeaths() {
        this.deaths++;
    }

    @Override
    public int getKilledNpc() {
        return this.killedNpc;
    }

    @Override
    public void incrementKilledNpc() {
        this.killedNpc++;
    }

    @Override
    public int getGrabbedMoney() {
        return this.grabbedMoney;
    }

    @Override
    public void setGrabbedMoney(int amount) {
        this.grabbedMoney+=amount;
        if (amount > getMaxMoney()) {
            this.setMaxMoney(amount);
        }
    }

    @Override
    public int getMaxMoney() {
        return this.maxMoney;
    }

    /**
     * method to set the new record for max money reached in one game
     * @param amount amount of money earned on last game
     */
    private void setMaxMoney(int amount) {
        this.maxMoney = amount;
    }

    @Override
    public int getMoneySpent() {
        return this.moneySpent;
    }

    @Override
    public void setMoneySpent(int amount) {
        this.moneySpent+=amount;
    }

    @Override
    public int getMaxMeters() {
        return this.maxMeters;
    }

    /**
     * Method to set a new record for meters performed in one game
     * @param meters meters of last game
     */
    private void setMaxMeters(int meters) {
        this.maxMeters = meters;
    }

    @Override
    public int getTotalMeters() {
        return this.totalMeters;
    }

    @Override
    public void setTotalMeters(int meters) {
        this.totalMeters+=meters;
        if (meters > getTotalMeters()) {
            this.setMaxMeters(meters);
        }
    }

    @Override
    public int getGrabbedObjects() {
        return this.grabbedObjects;
    }

    @Override
    public void incrementGrabbedObjects() {
        this.grabbedObjects++;
    }
}
