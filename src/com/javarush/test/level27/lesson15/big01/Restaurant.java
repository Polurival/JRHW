package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

/**
 * Created by
 * Polurival on 03.03.2016.
 */
public class Restaurant
{
    public static void main(String[] args)
    {
        Waitor waitor = new Waitor();
        Cook cook = new Cook("Amigo");
        cook.addObserver(waitor);

        Tablet fifthTablet = new Tablet(5);
        fifthTablet.addObserver(cook);
        fifthTablet.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
