package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by
 * Polurival on 12.03.2016.
 */
public class OrderManager implements Observer
{
    Queue<Order> queue = new LinkedBlockingQueue<>();

    public OrderManager()
    {
        Thread daemonThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Set<Cook> cooks = StatisticEventManager.getInstance().getCooks();
                while (!Thread.currentThread().isInterrupted())
                {
                    if (queue.peek() != null)
                    {
                        for (Cook cook : cooks)
                        {
                            if (!cook.isBusy())
                            {
                                cook.startCookingOrder(queue.poll());
                            }
                        }
                    }
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e)
                    {
                    }
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    @Override
    public void update(Observable tablet, Object order)
    {
        queue.add((Order) order);
    }
}
