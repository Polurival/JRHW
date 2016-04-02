package com.javarush.test.level32.lesson15.big01;

import com.javarush.test.level32.lesson15.big01.listeners.FrameListener;
import com.javarush.test.level32.lesson15.big01.listeners.TabbedPaneChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by
 * Polurival on 02.04.2016.
 */
public class View extends JFrame implements ActionListener
{
    private Controller controller;
    private FrameListener frameListener;

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public View()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException |
                UnsupportedLookAndFeelException |
                IllegalAccessException |
                InstantiationException e)
        {
            ExceptionHandler.log(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    public void init()
    {
        initGui();
        frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    public void initMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor()
    {
        htmlTextPane.setContentType("text/html");
        JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", htmlScrollPane);

        JScrollPane textScrollPane = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", textScrollPane);

        tabbedPane.setPreferredSize(null);

        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui()
    {
        initMenuBar();
        initEditor();
        pack();
    }

    public void exit()
    {
        controller.exit();
    }

    public void selectedTabChanged()
    {

    }

    public boolean canUndo()
    {
        return false;
    }

    public boolean canRedo()
    {
        return false;
    }
}