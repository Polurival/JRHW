package com.javarush.test.level12.lesson04.task04;

/* Три метода возвращают минимальное из двух переданных в него чисел
Написать public static методы: int min(int, int), long min(long, long), double min(double, double).
Каждый метод должен возвращать минимальное из двух переданных в него чисел.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    //Напишите тут ваши методы
    public static int min(int x1, int x2)
    {
        return x1 < x2 ? x1 : x2;
    }
    public static long min(long x1, long x2)
    {
        return x1 < x2 ? x1 : x2;
    }
    public static double min(double x1, double x2)
    {
        return x1 < x2 ? x1 : x2;
    }
}
