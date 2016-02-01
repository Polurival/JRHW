package com.javarush.test.level18.lesson10.home09;

/* Файлы и исключения
Читайте с консоли имена файлов
Если файла не существует (передано неправильное имя файла), то
перехватить исключение, вывести в консоль переданное неправильное имя файла и завершить работу программы
Не забудьте закрыть все потоки
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fis = null;
        String file = null;
        try
        {

            while (true)
            {
                file = reader.readLine();
                fis = new FileInputStream(file);
            }
        }
        catch (IOException e)
        {
            System.out.println(file);
            reader.close();
            fis.close();
        }
        reader.close();
        fis.close();
    }
}
