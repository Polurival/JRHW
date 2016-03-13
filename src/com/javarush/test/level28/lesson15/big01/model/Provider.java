package com.javarush.test.level28.lesson15.big01.model;

/**
 * Created by
 * Polurival on 13.03.2016.
 */
public class Provider
{
    private Strategy strategy;

    public Provider(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }
}
