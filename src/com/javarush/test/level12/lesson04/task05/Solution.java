package com.javarush.test.level12.lesson04.task05;

/* Три метода возвращают максимальное из двух переданных в него чисел
Написать public static методы: int max(int, int), long max (long, long), double max (double, double).
Каждый метод должен возвращать максимальное из двух переданных в него чисел.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    //Напишите тут ваши методы
    public static int max(int x1, int x2)
    {
        return x1 > x2 ? x1 : x2;
    }

    public static long max(long x1, long x2)
    {
        return x1 > x2 ? x1 : x2;
    }

    public static double max(double x1, double x2)
    {
        return x1 > x2 ? x1 : x2;
    }
}
