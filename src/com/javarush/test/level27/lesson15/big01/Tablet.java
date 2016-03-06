package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by
 * Polurival on 03.03.2016.
 */
public class Tablet extends Observable
{
    private final static java.util.logging.Logger LOG = Logger.getLogger(Tablet.class.getName());

    private final int number;

    public Tablet(int number)
    {
        this.number = number;
    }

    public void createOrder()
    {
        Order order = null;
        try
        {
            order = new Order(this);
            if (!order.isEmpty())
            {
                ConsoleHelper.writeMessage(order.toString());
                new AdvertisementManager(order.getTotalCookingTime()).processVideos();
                setChanged();
                notifyObservers(order);
            }
        }
        catch (IOException e)
        {
            LOG.log(Level.SEVERE, "Console is unavailable.");
        }
        catch (NoVideoAvailableException e) {
            LOG.log(Level.INFO, "No video is available for the order " + order);
        }
    }

    @Override
    public String toString()
    {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
