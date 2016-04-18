package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

/**
 * Created by Polurival
 * on 17.04.16.
 */
public class Model
{
    private EventListener eventListener;
    public static int FIELD_SELL_SIZE = 20;

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }
}
