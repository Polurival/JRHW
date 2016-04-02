package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by
 * Polurival on 02.04.2016.
 */
public class HTMLFileFilter extends FileFilter
{
    @Override
    public boolean accept(File file)
    {
        String fileName = file.getName().toLowerCase();
        return (file.isDirectory() || fileName.endsWith(".html") || fileName.endsWith(".htm"));
    }

    @Override
    public String getDescription()
    {
        return "HTML и HTM файлы";
    }
}
