package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;

/**
 * Created by
 * Polurival on 03.03.2016.
 */
public class Restaurant
{
    public static void main(String[] args)
    {
        Tablet fifthTablet = new Tablet(5);
        fifthTablet.addObserver(new Cook("Amigo"));
        fifthTablet.createOrder();
    }
}
