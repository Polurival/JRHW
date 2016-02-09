package com.javarush.test.level25.lesson16.big01;

/**
 * Created by Polurival
 * on 07.02.2016.
 */
public class Canvas
{
    private int height;
    private int width;

    private char[][] matrix;


    public Canvas(int height, int width)
    {
        this.height = height;
        this.width = width;
        matrix = new char[height][width];
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public char[][] getMatrix()
    {
        return matrix;
    }
}
