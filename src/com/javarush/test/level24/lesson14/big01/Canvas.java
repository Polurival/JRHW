package com.javarush.test.level24.lesson14.big01;

/**
 * Created by
 * Polurival on 01.05.2016.
 */
public class Canvas
{
    private int width;
    private int height;
    private char[][] matrix;

    public Canvas(int width, int height)
    {
        this.width = width;
        this.height = height;
        matrix = new char[height][width];
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public char[][] getMatrix()
    {
        return matrix;
    }

    public void setPoint(double x, double y, char c)
    {
        int intX = (int) Math.round(x);
        int intY = (int) Math.round(y);

        //в) ничего не делать, если x<0 или y<0 или y>matrix.length или x>matrix[0].length
        if (!(intX < 0 || intY < 0 || intY > matrix.length || intX > matrix[0].length))
        {
            matrix[intY][intX] = c;
        }
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                if (matrix[i][j] != 0)
                {
                    setPoint(x + j, y + i, c);
                }
            }
        }
    }
}
