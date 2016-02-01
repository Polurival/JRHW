package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream(args[0]);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        HashMap<String, Double> hm = new HashMap<>();
        ArrayList<Double> list = new ArrayList<>();

        while (br.ready()) {
            String[] s = br.readLine().split(" ");
            double d = Double.parseDouble(s[1]);
            if (!hm.containsKey(s[0]))
                hm.put(s[0], d);
            else
                hm.put(s[0], hm.get(s[0]) + d);
        }

        for (String key : hm.keySet()) {
            list.add(hm.get(key));
        }
        Collections.sort(list, Collections.reverseOrder());

        double max = list.get(0);

        for (Map.Entry<String, Double> pair : hm.entrySet()) {
            if (pair.getValue() == max)
                System.out.println(pair.getKey());
        }

        fis.close();
        br.close();

    }
}
