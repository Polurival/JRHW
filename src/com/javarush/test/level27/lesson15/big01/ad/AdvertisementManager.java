package com.javarush.test.level27.lesson15.big01.ad;

/**
 * Created by
 * Polurival on 05.03.2016.
 */
public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private int timeSeconds;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos()
    {
        System.out.println("processVideos method calling");
    }
}
