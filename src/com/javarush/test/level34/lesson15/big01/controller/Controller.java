package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Model;
import com.javarush.test.level34.lesson15.big01.view.View;

/**
 * Created by Polurival
 * on 17.04.16.
 */
public class Controller
{
    private View view;
    private Model model;

    public Controller()
    {
        view = new View(this);
        model = new Model();
    }
}
