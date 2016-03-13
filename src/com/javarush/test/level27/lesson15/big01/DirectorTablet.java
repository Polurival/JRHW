package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;

/**
 * Created by
 * Polurival on 08.03.2016.
 */
public class DirectorTablet
{
    public void printAdvertisementProfit()
    {
        StatisticManager.getInstance().watchAdvertisementStatistic();
    }

    public void printCookWorkloading()
    {
        StatisticManager.getInstance().watchCookStatistic();
    }

    public void printActiveVideoSet()
    {

    }

    public void printArchivedVideoSet()
    {

    }
}
