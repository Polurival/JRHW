package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки

Пример вывода:
, 19
- 7
f 361
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        FileInputStream fis = new FileInputStream(args[0]);
        TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
        while (fis.available() > 0) {
            int b = fis.read();
            if (!tm.containsKey(b)) {

                    tm.put(b, 1);
            } else {
                tm.put(b, tm.get(b) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> pair : tm.entrySet()) {
            int b = pair.getKey();
            if (b != 10)
            {
                char c = (char) b;
                System.out.println(c + " " + pair.getValue());
            }
        }
        fis.close();
    }
}
