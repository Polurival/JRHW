package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть все потоки ввода-вывода
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

import java.io.*;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeSet<String> fileNames = new TreeSet<String>();
        while (true) {
            String s = reader.readLine();
            if ("end".equals(s)) break;
            fileNames.add(s);
        }

        String outFileName = fileNames.first().substring(0, fileNames.first().length() - 6);
        File file = new File(outFileName);
        if(!file.exists()){
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file);
        FileInputStream fis = null;
        for (String filename : fileNames) {
            fis = new FileInputStream(filename);
            byte[] buffer = new byte[fis.available()];
            while (fis.available() > 0) {
                int count = fis.read(buffer);
                fos.write(buffer, 0, count);
            }
        }
        reader.close();
        fos.close();
        fis.close();

    }
}
