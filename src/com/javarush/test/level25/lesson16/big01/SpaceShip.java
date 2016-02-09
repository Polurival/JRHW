package com.javarush.test.level25.lesson16.big01;

/**
 * Created by Polurival
 * on 07.02.2016.
 */
public class SpaceShip extends BaseObject
{

    private double dx = 0;

    public SpaceShip(int x, int y) {
        super(x, y, 3);
    }

    @Override
    public void move() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    public void moveLeft() {
        dx = -1;
    }

    public void moveRight() {
        dx = 1;
    }
}
