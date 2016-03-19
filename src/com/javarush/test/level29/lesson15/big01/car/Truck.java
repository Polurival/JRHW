package com.javarush.test.level29.lesson15.big01.car;

/**
 * Created by
 * Polurival on 19.03.2016.
 */
public class Truck extends Car
{
    public Truck(int type, int numberOfPassengers)
    {
        super(type, numberOfPassengers);
    }

    public Truck(int numberOfPassengers)
    {
        super(0, numberOfPassengers);
    }
}
