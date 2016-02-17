package com.javarush.test.level26.lesson10.home01;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Polurival
 * on 16.02.2016.
 */
public class Consumer implements Runnable
{
    protected BlockingQueue queue;

    public Consumer (BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try {
            while (true) {
                System.out.println(queue.take());
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
}
