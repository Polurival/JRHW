package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by
 * Polurival on 04.03.2016.
 */
public class Cook extends Observable implements Observer
{
    private final String name;

    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public void update(Observable tablet, Object arg)
    {
        Order order = (Order) arg;

        ConsoleHelper.writeMessage("Start cooking - " + order +
                ", cooking time " + order.getTotalCookingTime() + "min");

        CookedOrderEventDataRow eventDataRow =
                new CookedOrderEventDataRow(tablet.toString(), name, order.getTotalCookingTime() * 60, order.getDishes());
        StatisticManager.getInstance().register(eventDataRow);

        setChanged();
        notifyObservers(order);
    }
}
