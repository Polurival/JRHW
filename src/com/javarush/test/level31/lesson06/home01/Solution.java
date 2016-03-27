package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        String fileName = args[0];
        String zipName = args[1];

        Map<String, byte[]> tempZipEntries = new HashMap<>();
        byte[] buffer = new byte[2048];
        ByteArrayOutputStream baos;
        int len = 0;

        FileInputStream fis = new FileInputStream(fileName);
        baos = new ByteArrayOutputStream();
        while ((len = fis.read(buffer)) > 0)
        {
            baos.write(buffer, 0, len);
        }
        String args0FileName = "new/" + new File(fileName).getName();
        tempZipEntries.put(args0FileName, baos.toByteArray());

        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipName));
        ZipEntry zipEntry;
        while ((zipEntry = zis.getNextEntry()) != null)
        {
            String entryName = zipEntry.getName();
            baos = new ByteArrayOutputStream();
            while ((len = zis.read(buffer)) > 0)
            {
                baos.write(buffer, 0, len);
            }
            tempZipEntries.put(entryName, baos.toByteArray());
        }
        zis.close();

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName));
        for (Map.Entry<String, byte[]> entry : tempZipEntries.entrySet())
        {
            zos.putNextEntry(new ZipEntry(entry.getKey()));
            zos.write(entry.getValue());
            zos.closeEntry();
        }
        zos.close();
    }
}
