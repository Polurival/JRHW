package com.javarush.test.level29.lesson15.big01.car;

/**
 * Created by
 * Polurival on 19.03.2016.
 */
public class Cabriolet extends Car
{
    public Cabriolet(int type, int numberOfPassengers)
    {
        super(type, numberOfPassengers);
    }

    public Cabriolet(int numberOfPassengers)
    {
        super(2, numberOfPassengers);
    }
}
