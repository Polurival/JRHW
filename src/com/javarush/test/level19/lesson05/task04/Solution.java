package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();

        FileReader fr = new FileReader(file1);
        FileWriter fw = new FileWriter(file2);

        while (fr.ready()) {
            int b = fr.read();
            char c = (char) b;
            if (c == '.') c = '!';
            fw.write(c);
        }
        reader.close();
        fr.close();
        fw.close();
    }
}
