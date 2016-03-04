package com.javarush.test.level27.lesson15.big01.kitchen;

/**
 * Created by
 * Polurival on 03.03.2016.
 */
public enum Dish
{
    Fish,
    Steak,
    Soup,
    Juice,
    Water;

    public static String allDishesToString()
    {
        StringBuilder sb = new StringBuilder();
        for (Dish dish : Dish.values())
        {
            sb.append(dish);
            if (dish.ordinal() != Dish.values().length - 1)
            {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
