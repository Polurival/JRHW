package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.Player;
import com.javarush.test.level34.lesson15.big01.model.Box;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Polurival
 * on 18.04.16.
 */
public class Field extends JPanel
{
    private View view;
    private EventListener eventListener;

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public Field(View view)
    {
        this.view = view;
    }

    @Override
    public void paint(Graphics g)
    {
    }
}
