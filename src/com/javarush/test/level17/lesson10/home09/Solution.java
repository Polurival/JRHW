package com.javarush.test.level17.lesson10.home09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines
4. Если список allLines НЕ содержит каких-либо строк, которые есть в forRemoveLines, то
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Сигнатуру метода main не менять.  Метод joinData должен вызываться в main.
*/

public class Solution
{
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file1 = reader.readLine();
            String file2 = reader.readLine();

            FileReader fr1 = new FileReader(file1);
            BufferedReader bf1 = new BufferedReader(fr1);
            FileReader fr2 = new FileReader(file2);
            BufferedReader bf2 = new BufferedReader(fr2);

            while (true)
            {
                String s = bf1.readLine();
                if (s == null) break;
                allLines.add(s);
            }
            while (true)
            {
                String s = bf2.readLine();
                if (s == null) break;
                forRemoveLines.add(s);
            }

            Solution sol = new Solution();
            sol.joinData();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void joinData() throws CorruptedDataException
    {
        boolean flag = false;
        for (int i = 0; i < forRemoveLines.size(); i++)
        {
            if (!allLines.contains(forRemoveLines.get(i)))
            {
                allLines.clear();
                throw new CorruptedDataException();

            }
            if ((i == forRemoveLines.size() - 1) && allLines.contains(forRemoveLines.get(i)))
                flag = true;

        }
        if (flag)
        {
            for (String forRemoveLine : forRemoveLines)
            {
                allLines.remove(allLines.indexOf(forRemoveLine));
            }
        }

    }
}
