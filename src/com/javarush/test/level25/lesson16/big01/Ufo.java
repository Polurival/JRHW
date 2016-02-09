package com.javarush.test.level25.lesson16.big01;

/**
 * Created by Polurival
 * on 07.02.2016.
 */
public class Ufo extends BaseObject
{
    int hod = 0;

    //картинка нло для отрисовки
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1},
    };

    public Ufo(double x, double y)
    {
        super(x, y, 3);
    }

    @Override
    public void move() {

        double dx = Math.random() * 2 - 1;
        double dy = Math.random() * 2 - 1;

        x = x + dx;

        if (y <= Space.game.getHeight() / 2)
        {
            y = y + dy;
        }

        checkBorders(radius, Space.game.getWidth() - radius + 1, 1, Space.game.getHeight() + 1);

        hod++;
        if (hod == 10) {
            fire();
            hod = 0;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'M');
    }

    public void fire() {
        Space.game.getBombs().add(new Bomb(x, y));
    }
}
