package com.javarush.test.level25.lesson16.big01;

/**
 * Created by Polurival
 * on 07.02.2016.
 */
public class Bomb extends BaseObject
{
    public Bomb(double x, double y, double radius)
    {
        super(x, y, radius);
    }

    public Bomb(double x, double y) {
        super(x, y, 1);
    }

    @Override
    public void move() {
        super.setY(super.getY() + 1);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setPoint(x, y, 'B');
    }
}
