package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by
 * Polurival on 03.03.2016.
 */
public class Tablet
{
    public static Logger logger = Logger.getLogger(Tablet.class.getName());

    private final int number;

    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    public Tablet(int number)
    {
        this.number = number;
    }

    public void createOrder()
    {
        try
        {
            Order order = new Order(this);
            printOrderAndShowAds(order);
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    public void createTestOrder()
    {
        try
        {
            TestOrder order = new TestOrder(this);
            printOrderAndShowAds(order);
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    public void printOrderAndShowAds(Order order)
    {
        ConsoleHelper.writeMessage(order.toString());
        AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
        try
        {
            advertisementManager.processVideos();
        }
        catch (NoVideoAvailableException e)
        {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
        queue.add(order);
    }

    @Override
    public String toString()
    {
        return "of Tablet{number=" + number + "}";
    }
}
