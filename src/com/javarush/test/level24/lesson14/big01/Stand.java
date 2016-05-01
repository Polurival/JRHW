package com.javarush.test.level24.lesson14.big01;

/**
 * Created by
 * Polurival on 01.05.2016.
 */
public class Stand extends BaseObject
{
    private double speed;
    private double direction;

    public Stand(double x, double y)
    {
        super(x, y, 3);
        speed = 1;
        direction = 0;
    }

    public double getSpeed()
    {
        return speed;
    }

    public double getDirection()
    {
        return direction;
    }

    @Override
    public void draw(Canvas canvas)
    {

    }

    @Override
    public void move()
    {

    }
}
