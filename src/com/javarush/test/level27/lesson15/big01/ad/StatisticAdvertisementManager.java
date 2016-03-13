package com.javarush.test.level27.lesson15.big01.ad;

/**
 * Created by
 * Polurival on 14.03.2016.
 */
public class StatisticAdvertisementManager
{
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance()
    {
        return instance;
    }

    private StatisticAdvertisementManager()
    {
    }
}
