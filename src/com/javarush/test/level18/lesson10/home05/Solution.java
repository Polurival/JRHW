package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать во второй файл
Закрыть потоки
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        ArrayList<Double> list = new ArrayList<Double>();
        String temp = "";

        while (fis.available() > 0) {
            int b = fis.read();
            if ((b == 32 || fis.available() == 0) && !"".equals(temp)) {
                if (fis.available() == 0) {
                    temp += (char) b;
                    double d = Double.parseDouble(temp);
                    list.add(d);
                } else {
                    double d = Double.parseDouble(temp);
                    list.add(d);
                    temp = "";
                }
            } else {
                temp += (char) b;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            String x = String.valueOf(Math.round(list.get(i)));
            byte[] b = new byte[x.length()];
            for (int j = 0; j < x.length(); j++) {
               b[j] = (byte) x.charAt(j);
            }
            fos.write(b);
            fos.write(32);
        }
        fis.close();
        fos.close();
    }
}
