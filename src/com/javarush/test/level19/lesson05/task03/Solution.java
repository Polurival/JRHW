package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки ввода-вывода.

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();

        Scanner scan = new Scanner(new File(file1));
        FileWriter fw = new FileWriter(file2);

        while (scan.hasNext()) {
            String s = scan.nextLine();
            String[] temp = s.split("[,;:.!?\\s]+");

            for (int i = 0; i < temp.length; i++) {
                boolean flag = true;
                for (int j = 0; j < temp[i].length(); j++) {
                    if (!Character.isDigit(temp[i].charAt(j))) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    fw.write(temp[i] + " ");
            }
        }
        scan.close();
        fw.close();
    }
}
