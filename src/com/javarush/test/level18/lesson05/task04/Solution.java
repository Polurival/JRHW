package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки ввода-вывода
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String file1 = r.readLine();
        String file2 = r.readLine();
        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);
        while (fis.available() > 0) {
            int[] bytes = new int[fis.available()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = fis.read();
            }
            for (int i = bytes.length - 1; i >= 0; i--) {
                fos.write(bytes[i]);
            }
        }
    }
}
