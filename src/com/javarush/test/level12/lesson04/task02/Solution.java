package com.javarush.test.level12.lesson04.task02;

/* print(int) и print(Integer)
Написать два метода: print(int) и print(Integer).
Написать такой код в методе main, чтобы вызвались они оба.
*/

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(print(5));
        System.out.println(print(6));

    }

    //Напишите тут ваши методы
    public static int print(int x)
    {
        return x*4;
    }
    public static int print(Integer x2)
    {
        return x2;
    }
}
