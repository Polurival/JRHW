package com.javarush.test.level27.lesson15.big01;

import java.util.List;

/**
 * Created by
 * Polurival on 12.03.2016.
 */
public class RandomOrderGeneratorTask implements Runnable
{
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval)
    {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run()
    {
        while (!Thread.currentThread().isInterrupted())
        {
            int number = (int) (Math.random() * tablets.size());
            tablets.get(number).createTestOrder();
            try
            {
                Thread.sleep(interval);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
