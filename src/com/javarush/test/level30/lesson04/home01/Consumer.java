package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by
 * Polurival on 20.03.2016.
 */
public class Consumer implements Runnable
{
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
        }
        while (!Thread.currentThread().isInterrupted())
        {
            try
            {
                ShareItem item = queue.take();
                System.out.println("Processing " + item.toString());
            }
            catch (InterruptedException e)
            {
                return;
            }
        }

    }
}
