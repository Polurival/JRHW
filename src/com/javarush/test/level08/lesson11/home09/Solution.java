package com.javarush.test.level08.lesson11.home09;

import java.util.Date;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args)
    {

        System.out.println(isDateOdd("MAY 29 2013"));
    }

    public static boolean isDateOdd(String date)
    {
        Date yearFirstDay = new Date(date);
        yearFirstDay.setHours(0);
        yearFirstDay.setMinutes(0);
        yearFirstDay.setSeconds(0);
        yearFirstDay.setDate(1);
        yearFirstDay.setMonth(0);

        Date currentDay = new Date(date);

        long msTimeDistance = currentDay.getTime() - yearFirstDay.getTime();
        long msDay = 24 * 60 * 60 * 1000; //сколько миллисекунд в одних сутках
        int dayCount = (int) (msTimeDistance / msDay);

        return (dayCount % 2) == 0;


    }
}
