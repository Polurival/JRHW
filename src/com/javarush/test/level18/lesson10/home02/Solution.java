package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран частоту встречания пробела. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
Закрыть потоки
*/

import com.javarush.test.level06.lesson08.task05.StringHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        FileInputStream fis = new FileInputStream(args[0]);
        int allCharsCount = fis.available();
        int gapCount = 0;
        while (fis.available() > 0) {
            int b = fis.read();
            if (b == 32) {
                gapCount++;
            }
        }
        double gapFreq = (double) gapCount / allCharsCount * 100;
        System.out.println(String.format(Locale.ENGLISH, "%.2f", gapFreq));
        fis.close();
    }
}
