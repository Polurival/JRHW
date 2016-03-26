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
    public static void main(String[] args) throws IOException
    {
        //Создали файлы (Пункт 1)
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/" + "allFilesContent.txt");
        //Проверяем директорию на наличее такого файла
        if (allFilesContent.exists())
        {
            allFilesContent.delete();
        }
        //Список файлов и дальнейшая обработка
        ArrayList<File> list = new ArrayList<>();
        //Удаляем файлы более 50кб (Пункты 2.1 и 2.2)
        seekMinFiles(path, resultFileAbsolutePath, list);
        //Сортируем компаратором (Пункт 2.2.1)
        Collections.sort(list, new Comparator<File>()
        {
            @Override
            public int compare(File o1, File o2)
            {
                return o1.getName().compareTo(o2.getName());
            }
        });
        //Пишем данные в файл (Пункт 2.2.3)
        FileOutputStream fileOutputStream = new FileOutputStream(resultFileAbsolutePath);
        for (int i = 0; i < list.size(); i++)
        {
            FileInputStream fileInputStream = new FileInputStream(list.get(i));
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            fileOutputStream.write(buffer);
            if (i == list.size() - 1)
            {
                continue;
            } else
            {
                fileOutputStream.write("\r".getBytes());
                fileOutputStream.write("\n".getBytes());
            }
            fileInputStream.close();
        }
        fileOutputStream.close();
        //ReNameFile (Пункт 2.2.2)
        resultFileAbsolutePath.renameTo(allFilesContent);
        //Удаляем пустые директории (Пункт 2.3)
        delDirect(path);
    }

    // Методы поиска минимального файла
    public static void seekMinFiles(File file, File resultFileAbsolutePath, ArrayList<File> list)
    {
        if (file.equals(resultFileAbsolutePath))
        {
            return;
        }
        if (file.isDirectory())
        {
            for (File directory : file.listFiles())
            {
                seekMinFiles(directory, resultFileAbsolutePath, list);
            }
        } else if (file.isFile())
        {
            if (file.length() > 50) file.delete();
            else list.add(file);
        }
    }

    //Удаление пустых папок
    public static void delDirect(File file)
    {
        if (file.isDirectory())
        {
            for (File direct : file.listFiles())
            {
                delDirect(direct);
            }
            file.delete();
        }
    }
}
