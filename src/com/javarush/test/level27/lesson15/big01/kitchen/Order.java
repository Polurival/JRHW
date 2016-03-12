package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by
 * Polurival on 03.03.2016.
 */
public class Order
{
    private Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
        initDishes();
    }

    public Tablet getTablet()
    {
        return tablet;
    }

    public List<Dish> getDishes()
    {
        return dishes;
    }

    public int getTotalCookingTime()
    {
        int sum = 0;
        for (Dish dish : dishes)
        {
            sum += dish.getDuration();
        }
        return sum;
    }

    public boolean isEmpty()
    {
        return dishes.isEmpty();
    }

    protected void initDishes() throws IOException
    {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString()
    {
        if (dishes.isEmpty())
        {
            return "";
        }
        return "Your order: " + dishes + " " + tablet;
    }
}
