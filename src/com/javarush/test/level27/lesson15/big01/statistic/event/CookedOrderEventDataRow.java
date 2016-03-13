package com.javarush.test.level27.lesson15.big01.statistic.event;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.util.Date;
import java.util.List;

/**
 * Created by
 * Polurival on 08.03.2016.
 */
public class CookedOrderEventDataRow implements EventDataRow
{
    private String cookName;
    private int cookingTimeSeconds;
    private List<Dish> cookingDishes;
    private String tabletName;
    private Date currentDate;

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishes)
    {
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishes = cookingDishes;
        currentDate = new Date();
    }

    @Override
    public EventType getType()
    {
        return EventType.COOKED_ORDER;
    }

    @Override
    public Date getDate()
    {
        return currentDate;
    }

    @Override
    public int getTime()
    {
        return cookingTimeSeconds;
    }

    public String getCookName()
    {
        return cookName;
    }
}
