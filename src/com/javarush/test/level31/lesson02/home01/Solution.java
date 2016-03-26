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
    public static void main(String[] args)
    {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);

        List<File> filesLessThan50kbList = new ArrayList<>();
        addFilesLessThan50kb(path, filesLessThan50kbList);
        Collections.sort(filesLessThan50kbList, new Comparator<File>()
        {
            @Override
            public int compare(File o1, File o2)
            {
                return o1.getName().compareTo(o2.getName());
            }
        });

        File newFile = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        resultFileAbsolutePath.renameTo(newFile);

        try (FileWriter writer = new FileWriter(newFile, true))
        {
            for (File f : filesLessThan50kbList)
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


        deleteEmptyDirectories(path);
        int x = 0;
    }

    private static void addFilesLessThan50kb(File file, List<File> list)
    {
        for (File f : file.listFiles())
        {
            if (f.isDirectory())
            {
                addFilesLessThan50kb(f, list);
            } else if (f.length() > 50)
            {
                f.delete();
            } else
            {
                list.add(f);
            }
        }
    }

    private static void deleteEmptyDirectories(File file)
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
                    deleteEmptyDirectories(f);
                }
            }
        }
    }
}
