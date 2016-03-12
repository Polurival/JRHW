package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Order;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by
 * Polurival on 12.03.2016.
 */
public class OrderManager implements Observer
{
    Queue<Order> queue = new LinkedBlockingQueue<>();

    @Override
    public void update(Observable tablet, Object order)
    {
        queue.add((Order) order);
    }
}
