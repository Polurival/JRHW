package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream stream = new FileInputStream(reader.readLine());
        String s = "";
        while (stream.available() > 0)
        {
            s += (char) stream.read();
        }

        String[] s2 = s.split("\\r\\n");
        int[] ints = new int[s2.length];
        for (int i = 0; i < ints.length; i++)
        {
            ints[i] = Integer.parseInt(s2[i]);
        }
        java.util.Arrays.sort(ints);

        for (int i = 0; i < ints.length; i++)
        {
            if (ints[i] % 2 == 0)
                System.out.println(ints[i]);
        }

        reader.close();
        stream.close();
    }
}
