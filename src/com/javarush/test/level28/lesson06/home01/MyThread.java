package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by
 * Polurival on 13.03.2016.
 */
public class MyThread extends Thread
{
    private static final AtomicInteger counter = new AtomicInteger(0);

    private void customSetPriority()
    {
        int curPriority = counter.incrementAndGet();
        if (curPriority > Thread.MAX_PRIORITY) {
            curPriority -= Thread.MAX_PRIORITY;
        }
        super.setPriority(curPriority);
    }

    private void customSetPriorityConsideringGroup(ThreadGroup group)
    {
        int curPriority = counter.incrementAndGet();
        int groupPriority = group.getMaxPriority();

        while (true)
        {
            if (curPriority > Thread.MAX_PRIORITY)
            {
                curPriority -= Thread.MAX_PRIORITY;
            }
            if (curPriority > groupPriority && curPriority <= Thread.MAX_PRIORITY)
            {
                curPriority = groupPriority;
            }

            if (curPriority <= Thread.MAX_PRIORITY) {
                break;
            }
        }

        super.setPriority(curPriority);
    }

    public MyThread()
    {
        customSetPriority();
    }

    public MyThread(Runnable target)
    {
        super(target);
        customSetPriority();
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        customSetPriorityConsideringGroup(group);
    }

    public MyThread(String name)
    {
        super(name);
        customSetPriority();
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        customSetPriorityConsideringGroup(group);
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        customSetPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        customSetPriorityConsideringGroup(group);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        customSetPriorityConsideringGroup(group);
    }
}
