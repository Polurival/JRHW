package com.javarush.test.level40.lesson10.task02;

/* Работа с Joda Time
Выполни задание, используя библиотеку Joda Time версии 2.9.1
Реализуй метод printDate(String date).
Он должен в качестве параметра принимать дату (в одном из 3х форматов)
и выводить ее в консоль в соответсвии с примером:

1) Для "21.4.2014 15:56:45" вывод должен быть:
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1
AM или PM: 1
Часы: 3
Часы дня: 15
Минуты: 56
Секунды: 45

2) Для "21.4.2014":
День: 21
День недели: 2
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 3
Год: 2014
Эра: 1

3) Для "17:33:40":
AM или PM: 1
Часы: 5
Часы дня: 17
Минуты: 33
Секунды: 40
*/

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Solution
{
    public static void main(String[] args)
    {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date)
    {
        //напишите тут ваш код
        LocalDateTime localDateTime;

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm:ss");

        String[] yearMonthDay;
        String[] hourMinuteSecond;
        String[] dateArr = date.split(" ");

        if (dateArr.length == 2)
        {
            localDateTime = dateTimeFormatter.parseLocalDateTime(date);

            showDateInfo(localDateTime);
            showTimeInfo(localDateTime);

        } else
        {

            yearMonthDay = dateArr[0].split("\\.");
            hourMinuteSecond = dateArr[0].split(":");

            if (yearMonthDay.length == 3)
            {
                localDateTime = dateFormatter.parseLocalDateTime(date);
                showDateInfo(localDateTime);

            } else if (hourMinuteSecond.length == 3)
            {
                localDateTime = timeFormatter.parseLocalDateTime(date);
                showTimeInfo(localDateTime);
            }
        }
    }

    private static void showTimeInfo(LocalDateTime localDateTime)
    {
        System.out.println("AM или PM: " + (localDateTime.getHourOfDay() > 11 ? 1 : 0));
        System.out.println("Часы: " +
                (localDateTime.getHourOfDay() > 11 ?
                        localDateTime.getHourOfDay() - 12 : localDateTime.getHourOfDay()));
        System.out.println("Часы дня: " + localDateTime.getHourOfDay());
        System.out.println("Минуты: " + localDateTime.getMinuteOfHour());
        System.out.println("Секунды: " + localDateTime.getSecondOfMinute());
    }

    private static void showDateInfo(LocalDateTime localDateTime)
    {
        System.out.println("День: " + localDateTime.getDayOfMonth());
        System.out.println("День недели: " + (localDateTime.getDayOfWeek() + 1));
        System.out.println("День месяца: " + localDateTime.getDayOfMonth());
        System.out.println("День года: " + localDateTime.getDayOfYear());
        System.out.println("Неделя месяца: " + (localDateTime.getDayOfMonth() / 7 + 1));
        System.out.println("Неделя года: " + localDateTime.getWeekOfWeekyear());
        System.out.println("Месяц: " + (localDateTime.getMonthOfYear() - 1));
        System.out.println("Год: " + localDateTime.getYear());
        System.out.println("Эра: " + localDateTime.getEra());
    }
}
