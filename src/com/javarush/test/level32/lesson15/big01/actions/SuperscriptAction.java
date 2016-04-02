package com.javarush.test.level32.lesson15.big01.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

/**
 * Created by
 * Polurival on 02.04.2016.
 */
public class SuperscriptAction extends StyledEditorKit.StyledTextAction
{
    /**
     * Creates a new StyledTextAction from a string action name.
     *
     * @param nm the name of the action
     */
    public SuperscriptAction(String nm)
    {
        super(nm);
    }

    public SuperscriptAction()
    {
        super(null);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
