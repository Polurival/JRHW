package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        List<String> params = new ArrayList<String>();
        List<String> values = new ArrayList<String>();
        String paramName = "";
        String paramValue = "";
        //double dblValue = 0;

        boolean flagParams = false;
        boolean flagValues = false;
        boolean flagString = false;

        for (int i = 0; i < url.length(); i++)
        {
            if ("?".equals(String.valueOf(url.charAt(i))))
            {
                flagParams = true;
                continue;
            }

            if ("=".equals(String.valueOf(url.charAt(i))))
            {
                flagParams = false;
                flagValues = true;

                params.add(paramName);
                paramName = "";
                continue;
            }

            if ("&".equals(String.valueOf(url.charAt(i))) && (paramValue.equals("")))
            {
                flagParams = true;
                flagValues = false;

                params.add(paramName);
                values.add(null);
                paramName = "";
                paramValue = "";
                continue;
            }
            else if ("&".equals(String.valueOf(url.charAt(i))))
            {
                flagParams = true;
                flagValues = false;

                values.add(paramValue);
                paramValue = "";
                continue;
            }

            if (flagValues)
            {
                paramValue += String.valueOf(url.charAt(i));
            }

            if (flagParams)
            {
                paramName += String.valueOf(url.charAt(i));
            }

            if (i == url.length() - 1)
            {
                if (!paramName.equals(""))
                    params.add(paramName);
                if (!paramValue.equals(""))
                    values.add(paramValue);
            }

        }

        for (int i = 0; i < params.size(); i++)
        {
            if (i == params.size() - 1)
                System.out.print(params.get(i));
            else
                System.out.print(params.get(i) + " ");
        }

        System.out.println();

        for (int i = 0; i < params.size(); i++)
        {
            if ("obj".equals(params.get(i)))
            {
                for (int j = 0; j < values.get(i).length(); j++)
                {
                    if (Character.isDigit(values.get(i).charAt(j)) || (values.get(i).charAt(j) == '.'))
                    {
                        if (j == values.get(i).length() - 1) alert(Double.parseDouble(values.get(i)));
                    }
                    else flagString = true;
                }
                if (flagString) alert(values.get(i));

            }
        }

    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
