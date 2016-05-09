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

import org.joda.time.DateTime;
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
        DateTime dateTime;

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm:ss");

        String[] yearMonthDay;
        String[] hourMinuteSecond;
        String[] dateArr = date.split(" ");

        if (dateArr.length == 2)
        {
            dateTime = dateTimeFormatter.parseDateTime(date);

            showDateInfo(dateTime);
            showTimeInfo(dateTime);

        } else
        {

            yearMonthDay = dateArr[0].split("\\.");
            hourMinuteSecond = dateArr[0].split(":");

            if (yearMonthDay.length == 3)
            {
                dateTime = dateFormatter.parseDateTime(date);
                showDateInfo(dateTime);

            } else if (hourMinuteSecond.length == 3)
            {
                dateTime = timeFormatter.parseDateTime(date);
                showTimeInfo(dateTime);
            }
        }
    }

    private static void showTimeInfo(DateTime dateTime)
    {
        System.out.println("AM или PM: " + (dateTime.getHourOfDay() > 11 ? 1 : 0));
        System.out.println("Часы: " + (dateTime.getHourOfDay() % 12));
        System.out.println("Часы дня: " + dateTime.getHourOfDay());
        System.out.println("Минуты: " + dateTime.getMinuteOfHour());
        System.out.println("Секунды: " + dateTime.getSecondOfMinute());
    }

    private static void showDateInfo(DateTime dateTime)
    {
        System.out.println("День: " + dateTime.getDayOfMonth());
        System.out.println("День недели: " + (dateTime.getDayOfWeek() + 1));
        System.out.println("День месяца: " + dateTime.getDayOfMonth());
        System.out.println("День года: " + dateTime.getDayOfYear());

        DateTime minYearDate = dateTime.dayOfYear().withMinimumValue();
        DateTime minMonthDate = dateTime.dayOfMonth().withMinimumValue();
        int weekDis = (minYearDate.getWeekyear() == dateTime.getYear()) ? 0 : 1;
        int weekOfYear = dateTime.getWeekOfWeekyear() + weekDis;
        if (weekOfYear >= 53)
            weekOfYear = 1;
        int weekOfMonth = dateTime.getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 1;
        if (minMonthDate.getWeekOfWeekyear() >= dateTime.getWeekOfWeekyear())
            weekOfMonth = dateTime.minusDays(7).getWeekOfWeekyear() - minMonthDate.getWeekOfWeekyear() + 2;
        if (dateTime.getMonthOfYear() == 1)
            weekOfMonth = weekOfYear;

        System.out.println("Неделя месяца: " + weekOfMonth);
        System.out.println("Неделя года: " + weekOfYear);
        System.out.println("Месяц: " + (dateTime.getMonthOfYear() - 1));
        System.out.println("Год: " + dateTime.getYear());
        System.out.println("Эра: " + dateTime.getEra());
    }
}
