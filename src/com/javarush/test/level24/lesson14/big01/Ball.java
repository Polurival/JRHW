package com.javarush.test.level24.lesson14.big01;

/**
 * Created by
 * Polurival on 01.05.2016.
 */

public class Ball extends BaseObject
{
    private double speed;
    private double direction;
    private double dx;
    private double dy;
    private boolean isFrozen;

    public Ball(double x, double y, double speed, double direction)
    {
        super(x, y, 1);
        this.speed = speed;
        this.direction = direction;
        isFrozen = true;
    }

    public double getSpeed()
    {
        return speed;
    }

    public double getDirection()
    {
        return direction;
    }

    public double getDx()
    {
        return dx;
    }

    public double getDy()
    {
        return dy;
    }

    public boolean isFrozen()
    {
        return isFrozen;
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.setPoint(x, y, 'O');
    }

    @Override
    public void move()
    {
        if (!isFrozen)
        {
            x += dx;
            y += dy;
        }
    }

    public void start()
    {
        isFrozen = false;
    }
}
