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
}
