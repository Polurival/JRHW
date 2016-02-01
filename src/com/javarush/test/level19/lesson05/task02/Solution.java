package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть поток ввода.
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();

        Scanner scan = new Scanner(new File(file));
        int count = 0;
        while (scan.hasNext()) {
            String s = scan.nextLine();
            String[] temp = s.split("[,;:.!?\\s]+");

            for (String word : temp) {
                if ("world".equals(word))
                    count++;
            }
        }
        System.out.println(count);
        reader.close();
        scan.close();
    }
}
