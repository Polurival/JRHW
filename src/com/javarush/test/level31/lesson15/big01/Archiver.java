package com.javarush.test.level31.lesson15.big01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by
 * Polurival on 27.03.2016.
 */
public class Archiver
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Введите полный путь архива");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String archivePathName = reader.readLine();
            ZipFileManager zipFileManager = new ZipFileManager(Paths.get(archivePathName));

            System.out.println("Введите полный путь файла для архивации");
            String filePathName = reader.readLine();
            zipFileManager.createZip(Paths.get(filePathName));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
