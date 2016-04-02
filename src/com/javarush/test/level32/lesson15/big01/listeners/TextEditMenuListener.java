package com.javarush.test.level32.lesson15.big01.listeners;

import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Created by
 * Polurival on 02.04.2016.
 */
public class TextEditMenuListener implements MenuListener
{
    private View view;

    public TextEditMenuListener(View view)
    {
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent menuEvent)
    {
        JMenu menu = (JMenu) menuEvent.getSource();
        Component[] menuComponents = menu.getMenuComponents();
        for (Component component : menuComponents) {
            component.setEnabled(view.isHtmlTabSelected());
        }
    }

    @Override
    public void menuDeselected(MenuEvent menuEvent)
    {

    }

    @Override
    public void menuCanceled(MenuEvent menuEvent)
    {

    }
}
