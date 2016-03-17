package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.Model;

import java.io.IOException;

/**
 * Created by
 * Polurival on 13.03.2016.
 */
public class Controller
{
    private Model model;

    public Controller(Model model)
    {
        if (model == null)
        {
            throw new IllegalArgumentException();
        }
        this.model = model;
    }

    public void onCitySelect(String cityName) throws IOException
    {
        model.selectCity(cityName);
    }
}
