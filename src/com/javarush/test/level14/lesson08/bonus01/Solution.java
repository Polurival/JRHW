package com.javarush.test.level14.lesson08.bonus01;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //Add your code here
        //2
        try
        {
            InputStream fis = new FileInputStream("");

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //3
        try
        {
            throw new IOException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //4
        try
        {
            throw new ClassCastException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //5
        try
        {
            throw new ArrayStoreException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //6
        try
        {
            throw new IllegalArgumentException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //7
        try
        {
            throw new IllegalMonitorStateException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //8
        try
        {
            throw new IllegalStateException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //9
        try
        {
            throw new IllegalThreadStateException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //10
        try
        {
            throw new IndexOutOfBoundsException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }
    }
}