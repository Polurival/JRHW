package com.javarush.test.level26.lesson10.home02;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Polurival
 * on 17.02.2016.
 */
public class Producer implements Runnable
{
    protected ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run()
    {
        try
        {
            Thread currentThread = Thread.currentThread();
            int i = 1;
            while (!currentThread.isInterrupted())
            {
                map.put(String.valueOf(i), String.valueOf(i));
                System.out.print("Some text for ");
                i++;
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
