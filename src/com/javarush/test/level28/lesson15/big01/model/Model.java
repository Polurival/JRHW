package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * Polurival on 17.03.2016.
 */
public class Model
{
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers)
    {
        if (view == null || providers == null || providers.length == 0)
        {
            throw new IllegalArgumentException();
        }
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city) throws IOException
    {
        List<Vacancy> vacancies = new ArrayList<>();
        for (Provider provider : providers)
        {
            List<Vacancy> vacanciesFromCurrentProvider = provider.getJavaVacancies(city);
            vacancies.addAll(vacanciesFromCurrentProvider);
        }
        view.update(vacancies);
    }
}
