package com.javarush.test.level36.lesson08.task01;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        Set<String> set = new TreeSet<>();

        try (InputStream is = new FileInputStream(args[0]))
        {
            int b;
            String c;

            while (is.available() > 0)
            {
                b = is.read();
                if ((b >= 65 && b <= 90) || (b >= 97 && b <= 122))
                {
                    c = String.valueOf((char) b).toLowerCase();
                    set.add(c);
                }
            }
        }

        int i = 0;
        for (String str : set)
        {
            if (++i > 5)
            {
                break;
            }
            System.out.print(str);
        }
    }
}
