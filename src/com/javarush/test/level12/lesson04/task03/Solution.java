package com.javarush.test.level12.lesson04.task03;

/* Пять методов print с разными параметрами
Написать пять методов print с разными параметрами.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    //Напишите тут ваши методы
    public int print(int x)
    {
        return x;
    }

    public int print(int x, int y)
    {
        return x * y;
    }

    public long print(int x, long y)
    {
        return x * y;
    }

    public String print(String x, long y)
    {
        return x + y;
    }

    public double print(int x, long y, double f)
    {
        return x * y * f;
    }
}
