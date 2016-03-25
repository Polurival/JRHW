package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.util.*;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution
{
    private static List<File> filesLessThan50kbList = new ArrayList<>();

    public static void main(String[] args)
    {
        String path = args[0];
        String resultFileAbsolutePath = args[1];

        File file = new File(path);
        addFilesLessThan50kb(file);
        List<File> files = filesLessThan50kbList;
        Collections.sort(files, new Comparator<File>()
        {
            @Override
            public int compare(File o1, File o2)
            {
                return o1.getName().compareTo(o2.getName());
            }
        });
        resultFileAbsolutePath = "allFilesContent.txt";

        try (FileWriter writer = new FileWriter(resultFileAbsolutePath, true))
        {
            for (File f : files)
            {
                try (FileReader reader = new FileReader(f))
                {
                    char[] buffer = new char[(int) f.length()];
                    reader.read(buffer);
                    String fileContent = new String(buffer);
                    writer.write(fileContent + "\n");
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void addFilesLessThan50kb(File file)
    {
        for (File f : file.listFiles())
        {
            if (f.isDirectory())
            {

                if (f.listFiles().length == 0)
                {
                    f.delete();
                } else
                {
                    addFilesLessThan50kb(f);
                }

            } else if (f.length() > 50)
            {
                f.delete();
            } else
            {
                filesLessThan50kbList.add(f);
            }
        }
    }
}
