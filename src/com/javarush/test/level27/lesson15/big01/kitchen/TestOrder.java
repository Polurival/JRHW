package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by
 * Polurival on 12.03.2016.
 */
public class TestOrder extends Order
{
    public TestOrder(Tablet tablet) throws IOException
    {
        super(tablet);
    }

    @Override
    protected void initDishes()
    {
        dishes = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int i = random.nextInt(Dish.values().length);
        int j = random.nextInt(Dish.values().length);
        int k = random.nextInt(Dish.values().length);
        dishes.add(Dish.values()[j]);
        dishes.add(Dish.values()[i]);
        dishes.add(Dish.values()[k]);
    }
}
