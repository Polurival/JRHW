package com.javarush.test.level31.lesson15.big01.command;

import com.javarush.test.level31.lesson15.big01.ConsoleHelper;
import com.javarush.test.level31.lesson15.big01.ZipFileManager;

import java.nio.file.Paths;

/**
 * Created by
 * Polurival on 28.03.2016.
 */
public class ZipRemoveCommand extends ZipCommand
{
    @Override
    public void execute() throws Exception
    {
        ConsoleHelper.writeMessage("Удаление файла из архива.");

        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Введите файл для удаления:");
        String fileForRemoving = ConsoleHelper.readString();
        zipFileManager.removeFile(Paths.get(fileForRemoving));

        ConsoleHelper.writeMessage("Файл был удален.");
    }
}
