package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Order;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by
 * Polurival on 03.03.2016.
 */
public class Tablet
{
    private final static java.util.logging.Logger LOG = Logger.getLogger(Tablet.class.getName());

    public final int number;

    public Tablet(int number)
    {
        this.number = number;
    }

    public void createOrder()
    {
        try
        {
            Order order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
        }
        catch (IOException e)
        {
            LOG.log(Level.SEVERE, "Console is unavailable.");
        }
    }
}
