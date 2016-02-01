package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        FileInputStream fis = new FileInputStream(args[0]);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        TreeMap<String, Double> tm = new TreeMap<>();

        while (br.ready()) {
            String[] s = br.readLine().split(" ");
            double d = Double.parseDouble(s[1]);
            if (!tm.containsKey(s[0]))
                tm.put(s[0], d);
            else
                tm.put(s[0], tm.get(s[0]) + d);
        }

        for (Map.Entry<String, Double> pair : tm.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }

    }
}
