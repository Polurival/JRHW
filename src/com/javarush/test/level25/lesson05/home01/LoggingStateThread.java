package com.javarush.test.level25.lesson05.home01;

/**
 * Created by Юрец on 05.02.2016.
 */
public class LoggingStateThread extends Thread
{
    private Thread target;
    private State state;

    public LoggingStateThread(Thread target)
    {
        this.target = target;
        setDaemon(true);
    }

    @Override
    public void run()
    {
        while (!interrupted()) {
            if (target.getState() != state) {
                state = target.getState();
                System.out.println(state);
            }
            if (state == State.TERMINATED) {
                interrupt();
            }
        }
    }

}
