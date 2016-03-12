package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by
 * Polurival on 03.03.2016.
 */
public class Restaurant
{
    private static final int ORDER_CREATING_INTERVAL = 100;

    private static final LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args)
    {
        Cook amigo = new Cook("Amigo");
        amigo.setQueue(queue);
        Cook makarevich = new Cook("Makarevich");
        makarevich.setQueue(queue);

        Waitor waitor = new Waitor();
        amigo.addObserver(waitor);
        makarevich.addObserver(waitor);

        List<Tablet> tablets = new ArrayList<>(5);
        for (int i = 1; i <= 5; i++)
        {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(queue);
            tablets.add(tablet);
        }

        RandomOrderGeneratorTask task = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread orderThread = new Thread(task);
        orderThread.start();

        Thread amigoThread = new Thread(amigo);
        amigoThread.start();
        Thread makarevichThread = new Thread(makarevich);
        makarevichThread.start();

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
        }

        orderThread.interrupt();

        boolean isNotDone = true;
        while (isNotDone)
        {
            if (queue.isEmpty())
            {
                amigoThread.interrupt();
                makarevichThread.interrupt();
                isNotDone = false;
            }
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
