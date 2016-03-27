package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution
{
    public static void main(String[] args)
    {
        String resultFileName = args[0];
        List<String> fileNamePartList = new ArrayList<>();
        fileNamePartList.addAll(Arrays.asList(args).subList(1, args.length));

        Collections.sort(fileNamePartList, new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                return o1.compareTo(o2);
            }
        });

        List<FileInputStream> streams = new ArrayList<>();
        for (String fileNamePart : fileNamePartList)
        {
            try
            {
                streams.add(new FileInputStream(fileNamePart));
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        byte[] buffer = new byte[2048];
        int len = 0;

        try
        {
            FileOutputStream fos = new FileOutputStream(new File(resultFileName));
            ZipInputStream zis = new ZipInputStream(new SequenceInputStream(Collections.enumeration(streams)));

            while (zis.getNextEntry() != null)
            {
                while ((len = zis.read(buffer)) > 0)
                {
                    fos.write(buffer, 0, len);
                }
                zis.closeEntry();
            }
            zis.close();
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
