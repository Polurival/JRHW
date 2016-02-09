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
        this.matrix = new char[height][width];
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

    public void setPoint(double x, double y, char c) {
        int intX = (int) Math.round(x);
        int intY = (int) Math.round(y);

        matrix[intY][intX] = c;

        if (intX < 0 || intY < 0 || intY > matrix.length || intX > matrix[0].length) {
            return;
        }
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c) {

        int height = matrix.length;
        int width = matrix[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] != 0) {
                    setPoint(x + j, y + i, c);
                }
            }
        }
    }
}
