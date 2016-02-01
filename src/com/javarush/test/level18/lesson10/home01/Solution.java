package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        FileInputStream fis = new FileInputStream(args[0]);
        int engCharCount = 0;
        while (fis.available() > 0) {
            int b = fis.read();
            if ((b >= 65 && b <= 90) || (b >= 97 && b <= 122)) {
                engCharCount++;
            }
        }
        System.out.println(engCharCount);
        fis.close();
    }
}
