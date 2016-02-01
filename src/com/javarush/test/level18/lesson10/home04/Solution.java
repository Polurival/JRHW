package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        FileInputStream fis1 = new FileInputStream(file1);
        FileInputStream fis2 = new FileInputStream(file2);

        int count = fis1.available();
        int[] buffer = new int[count];


        for (int i = 0; i < count; i++) {
            buffer[i] = fis1.read();
        }

        FileOutputStream fos = new FileOutputStream(file1);
        while (fis2.available() > 0) {
            int b = fis2.read();
            fos.write(b);
        }

        for (int i = 0; i < count; i++) {
            byte b = (byte) buffer[i];
            fos.write(b);
        }

        fos.close();
        fis1.close();
        fis2.close();
    }
}
