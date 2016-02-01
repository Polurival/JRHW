package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести все строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();

        List<String> texts = new ArrayList<String>();
        while (true)
        {
            String text = reader.readLine();
            texts.add(text);
            if (text.equals("exit")) break;
        }

        OutputStream fos = new FileOutputStream(file);
        for (int i = 0; i < texts.size(); i++)
        {
            byte[] buffer = texts.get(i).getBytes();
            byte[] lineSeparator = System.lineSeparator().getBytes();
            fos.write(buffer);
            fos.write(lineSeparator);
        }
        reader.close();
        fos.close();

    }
}
