package it.unibo.jetpackjoyride.core.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jetpackjoyride.core.api.GadgetInfoPositions;
import it.unibo.jetpackjoyride.core.api.GameEconomy;
import it.unibo.jetpackjoyride.core.api.Saves;
import it.unibo.jetpackjoyride.core.api.SkinInfoPositions;
import it.unibo.jetpackjoyride.input.api.Input.TypeInput;
import it.unibo.jetpackjoyride.input.api.InputQueue;
import it.unibo.jetpackjoyride.input.impl.InputImpl;
import it.unibo.jetpackjoyride.model.api.Gadget;
import it.unibo.jetpackjoyride.model.api.SkinInfo;
import it.unibo.jetpackjoyride.model.api.Statistics;
import it.unibo.jetpackjoyride.model.impl.GadgetImpl;
import it.unibo.jetpackjoyride.model.impl.SkinInfoImpl;
import it.unibo.jetpackjoyride.model.impl.StatisticsImpl;
/**
 * Class to manage the game economy.
 * 
 * @author lorenzo.bacchini4@studio.unibo.it
 */
public class GameEconomyImpl implements GameEconomy {

    private final transient InputQueue inputQueue;
    /**
     * gadget is the class that contains the map
     * with the name of the gadget and the gadget information.
     * 
     * gadgetInfo will contain the information of the gadget.
     */
    private final Gadget gadget;
    private List<String> gadgetInfo;

    /**
     * skin is the class that contains the map
     * with the name of the skin and the gadget information.
     * 
     * skinInfo will contain the information of the skin.
     */
    private final SkinInfo skin;
    private List<String> skinInfo;

    /**
     * saves is the class to load game general statistic.
     * generalStatics is the class that contains the map
     * with the name of the statistic and the statistic value.
     */
    private final Saves saves;
    private final Statistics generalStatistics;

    private static final String TRUE = "true";
    private static final String FALSE = "false";

    /**
     * Constructor of the GameEconomyImpl class.
     * @param generalStatistics
     * @param inputQueue
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "inputQueue and generalStatistics must be the same object")
    public GameEconomyImpl(final InputQueue inputQueue, final Statistics generalStatistics) {
        this.gadget = new GadgetImpl();
        this.skin = new SkinInfoImpl();
        this.saves = new SavesImpl();
        this.inputQueue = inputQueue;
        this.generalStatistics = generalStatistics;
    }

    @Override
    public final void buyGadget(final String name) throws IOException {
        this.gadgetInfo = gadget.getValue(name);
        final int gadgetPrice = Integer.parseInt(
                gadgetInfo.get(GadgetInfoPositions.PRICE.ordinal()));
        final int actualMoney = this.generalStatistics.getValue(StatisticsImpl.ACTUAL_MONEY);
        if (actualMoney >= gadgetPrice) {
            gadgetInfo.set(GadgetInfoPositions.PURCHASED.ordinal(), TRUE);
            gadget.setValue(name, gadgetInfo);
            generalStatistics.increment(StatisticsImpl.ACTUAL_MONEY, -gadgetPrice);
            generalStatistics.increment(StatisticsImpl.MONEY_SPENT, gadgetPrice);
            saves.uploadSaves(generalStatistics.getAll());
        } else {
            this.inputQueue.addInput(new InputImpl(TypeInput.ERROR, "Not Enough Money to buy this gadget!"));
        }
    }

    @Override
    public final void enableGadget(final String name) {
        this.gadgetInfo = gadget.getValue(name);
        if (TRUE.equals(gadgetInfo.get(GadgetInfoPositions.PURCHASED.ordinal()))) {
            gadgetInfo.set(GadgetInfoPositions.STATE.ordinal(), TRUE);
            gadget.setValue(name, gadgetInfo);
        } else {
            this.inputQueue.addInput(new InputImpl(TypeInput.ERROR, "This gadget is not purchased!"));
        }
    }

    @Override
    public final void disableGadget(final String name) {
        this.gadgetInfo = gadget.getValue(name);
        gadgetInfo.set(GadgetInfoPositions.STATE.ordinal(), FALSE);
        gadget.setValue(name, gadgetInfo);
    }

    @Override
    public final void buySkin(final String name) throws IOException {
        this.skinInfo = skin.getValue(name);
        final int skinPrice = Integer.parseInt(
                skinInfo.get(SkinInfoPositions.PRICE.ordinal()));
        final int actualMoney = generalStatistics.getValue(StatisticsImpl.ACTUAL_MONEY);
        if (actualMoney >= skinPrice) {
            skinInfo.set(SkinInfoPositions.PURCHASED.ordinal(), TRUE);
            skin.setValue(name, skinInfo);
            generalStatistics.increment(StatisticsImpl.ACTUAL_MONEY, -skinPrice);
            generalStatistics.increment(StatisticsImpl.MONEY_SPENT, skinPrice);
            saves.uploadSaves(generalStatistics.getAll());
        } else {
            this.inputQueue.addInput(new InputImpl(TypeInput.ERROR, "Not Enough Money to buy this skin!"));
        }
    }

    @Override
    public final void selectSkin(final String name) {
        this.skinInfo = skin.getValue(name);
        if (TRUE.equals(skinInfo.get(SkinInfoPositions.PURCHASED.ordinal()))) {
            final Map<String, List<String>> allSkin = skin.getAll();

            allSkin.entrySet().stream()
                    .filter(e -> TRUE.equals(e.getValue().get(SkinInfoPositions.STATE.ordinal())))
                    .forEach(e -> {
                        e.getValue().set(SkinInfoPositions.STATE.ordinal(), FALSE);
                        skin.setValue(e.getKey(), e.getValue());
                    });
            skinInfo.set(GadgetInfoPositions.STATE.ordinal(), TRUE);
            skin.setValue(name, skinInfo);
        } else {
            this.inputQueue.addInput(new InputImpl(TypeInput.ERROR, "this skin is not purchased!"));
        }
    }

}
