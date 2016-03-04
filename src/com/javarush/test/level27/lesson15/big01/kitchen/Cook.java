package com.javarush.test.level27.lesson15.big01.kitchen;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by
 * Polurival on 04.03.2016.
 */
public class Cook extends Observable implements Observer
{
    private String name;

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
        System.out.println("Start cooking - " + order.toString() +
                ", cooking time " + order.getTotalCookingTime() + "min");
        setChanged();
        notifyObservers(order);
    }
}
