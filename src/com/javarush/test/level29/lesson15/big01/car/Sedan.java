package com.javarush.test.level29.lesson15.big01.car;

/**
 * Created by
 * Polurival on 19.03.2016.
 */
public class Sedan extends Car
{
    public Sedan(int type, int numberOfPassengers)
    {
        super(type, numberOfPassengers);
    }

    public Sedan(int numberOfPassengers)
    {
        super(1, numberOfPassengers);
    }
}
