package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * Polurival on 03.03.2016.
 */
public class Restaurant
{
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args)
    {
        Cook amigo = new Cook("Amigo");
        Cook makarevich = new Cook("Makarevich");

        StatisticEventManager statisticEventManager = StatisticEventManager.getInstance();
        statisticEventManager.register(amigo);
        statisticEventManager.register(makarevich);

        Waitor waitor = new Waitor();
        amigo.addObserver(waitor);
        makarevich.addObserver(waitor);

        List<Tablet> tablets = new ArrayList<>(5);
        OrderManager orderManager = new OrderManager();
        for (int i = 1; i <= 5; i++)
        {
            Tablet tablet = new Tablet(i);
            tablet.addObserver(orderManager);
            tablets.add(tablet);
        }

        RandomOrderGeneratorTask task = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(task);
        thread.start();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
        }
        thread.interrupt();


        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
