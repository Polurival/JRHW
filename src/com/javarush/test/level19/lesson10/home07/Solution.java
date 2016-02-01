package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));

        boolean isFirst = true;
        while (br.ready()) {
            String[] s = br.readLine().split(" ");

            for (int i = 0; i < s.length; i++) {
                if (s[i].length() > 6) {
                    if (isFirst) {
                        bw.write(s[i]);
                        isFirst = false;
                    } else {
                        bw.write("," + s[i]);
                    }
                }
            }

        }

        br.close();
        bw.close();

    }
}
