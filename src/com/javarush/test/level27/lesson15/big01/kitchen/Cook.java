package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;

/**
 * Created by
 * Polurival on 04.03.2016.
 */
public class Cook extends Observable
{
    private final String name;

    private boolean busy;

    public boolean isBusy()
    {
        return busy;
    }

    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public void startCookingOrder(Order order)
    {
        busy = true;
        try
        {
            Thread.sleep(10 * order.getTotalCookingTime());
        }
        catch (InterruptedException e)
        {
        }
        ConsoleHelper.writeMessage("Start cooking - " + order +
                ", cooking time " + order.getTotalCookingTime() + "min");

        CookedOrderEventDataRow eventDataRow =
                new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes());
        StatisticEventManager.getInstance().register(eventDataRow);

        setChanged();
        notifyObservers(order);
        busy = false;
    }
}
