package com.javarush.test.level25.lesson16.big01;

/**
 * Created by Polurival
 * on 07.02.2016.
 */
public class Rocket extends BaseObject
{
    public Rocket(double x, double y) {
        super(x, y, 1);
    }

    @Override
    public void move() {
        y--;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setPoint(x, y, 'R');
    }
}
