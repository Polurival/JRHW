package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by
 * Polurival on 04.03.2016.
 */
public class Cook extends Observable implements Runnable
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

    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        while (!Thread.currentThread().isInterrupted())
        {
            if (!queue.isEmpty())
            {
                Order order = queue.poll();
                if (order != null)
                {
                    this.startCookingOrder(order);
                }
                try
                {
                    Thread.sleep(10);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void startCookingOrder(Order order)
    {
        busy = true;

        ConsoleHelper.writeMessage("Start cooking - " + order +
                ", cooking time " + order.getTotalCookingTime() + "min");

        CookedOrderEventDataRow eventDataRow = new CookedOrderEventDataRow(
                order.getTablet().toString(),
                name,
                order.getTotalCookingTime() * 60,
                order.getDishes());
        StatisticEventManager.getInstance().register(eventDataRow);

        try
        {
            Thread.sleep(10 * order.getTotalCookingTime());
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }

        setChanged();
        notifyObservers(order);

        busy = false;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
