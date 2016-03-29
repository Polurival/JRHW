package com.javarush.test.level31.lesson15.big01.command;

import com.javarush.test.level31.lesson15.big01.ConsoleHelper;
import com.javarush.test.level31.lesson15.big01.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by
 * Polurival on 28.03.2016.
 */
public abstract class ZipCommand implements Command
{
    public ZipFileManager getZipFileManager() throws Exception
    {
        ConsoleHelper.writeMessage("Введите полный путь файла архива.");
        String fullPathStr = ConsoleHelper.readString();
        Path fullPath = Paths.get(fullPathStr);
        ZipFileManager zipFileManager = new ZipFileManager(fullPath);
        return zipFileManager;
    }
}
