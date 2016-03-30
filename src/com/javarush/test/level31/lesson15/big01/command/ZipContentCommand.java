package com.javarush.test.level31.lesson15.big01.command;

import com.javarush.test.level31.lesson15.big01.ConsoleHelper;
import com.javarush.test.level31.lesson15.big01.FileProperties;
import com.javarush.test.level31.lesson15.big01.ZipFileManager;

import java.util.List;

/**
 * Created by
 * Polurival on 28.03.2016.
 */
public class ZipContentCommand extends ZipCommand
{
    @Override
    public void execute() throws Exception
    {
        ConsoleHelper.writeMessage("Просмотр содержимого архива.");
        ZipFileManager zipFileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Содержимое архива:");
        List<FileProperties> filesPropertiesList = zipFileManager.getFilesList();
        for (FileProperties fileProperties : filesPropertiesList) {
            ConsoleHelper.writeMessage(fileProperties.toString());
        }
        ConsoleHelper.writeMessage("Содержимое архива прочитано.");
    }
}
