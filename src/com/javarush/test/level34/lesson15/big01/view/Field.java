package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.GameObject;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;

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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);
        GameObjects gameObjects = view.getGameObjects();
        for (GameObject gameObject : gameObjects.getAll())
        {
            gameObject.draw(g);
        }
    }
}
