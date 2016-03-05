package com.javarush.test.level27.lesson15.big01.ad;

/**
 * Created by
 * Polurival on 05.03.2016.
 */
public class AdvertisementStorage
{
    private static AdvertisementStorage instance = new AdvertisementStorage();

    public static AdvertisementStorage getInstance()
    {
        return instance;
    }

    private AdvertisementStorage() {
    }
}
