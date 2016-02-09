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

        x += dx;
        if (x - radius < 0 || x + radius > Space.game.getWidth() - 1) {

        }
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

    public void fire() {
        Rocket rocket1 = new Rocket(x - radius + 1, y);
        Rocket rocket2 = new Rocket(x + radius - 1, y);
        Space.game.getRockets().add(rocket1);
        Space.game.getRockets().add(rocket2);
    }
}
