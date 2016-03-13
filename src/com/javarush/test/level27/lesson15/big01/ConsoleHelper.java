package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * Polurival on 03.03.2016.
 */
public class ConsoleHelper
{
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> dishes = new ArrayList<>();
        while (true)
        {
            String input = readString();
            if ("exit".equals(input))
            {
                break;
            }

            try
            {
                Dish dish = Dish.valueOf(input);
                dishes.add(dish);
            }
            catch (IllegalArgumentException e)
            {
                writeMessage(input + " is not detected");
            }
        }
        return dishes;
    }

}
