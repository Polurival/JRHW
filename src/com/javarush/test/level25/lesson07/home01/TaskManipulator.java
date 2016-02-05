package com.javarush.test.level25.lesson07.home01;

public class TaskManipulator implements Runnable, CustomThreadManipulator
{
    private Thread thread;
    private String threadName;

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(0);
            if (!thread.isInterrupted()) {
                System.out.println(threadName);
            }

            while (!thread.isInterrupted())
            {
                Thread.sleep(90);
                System.out.println(threadName);

            }
        }
        catch (InterruptedException e)
        {
        }

    }

    @Override
    public void start(String threadName)
    {
        this.threadName = threadName;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop()
    {
        thread.interrupt();
    }
}
