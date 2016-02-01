package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file = reader.readLine();

            String tag = args[0];
            String otag = "<" + tag;
            String ctag = "</" + tag + ">";
            StringBuilder sb = new StringBuilder();

            FileReader fr = new FileReader(file);
            while (true)
            {
                int i = fr.read();
                if (i == -1) break;
                String c = ((char) i) + "";
                sb.append(c);

            }
            String s = sb.toString();
            s = s.replaceAll("\r\n", "");

            reader.close();
            fr.close();


            ArrayList<Integer> stLst = new ArrayList<>();
            ArrayList<Integer> enLst = new ArrayList<>();
            int start;
            int end;
            int k = 0; // для сохранения i
            int w = 0; // для тегов вида <tag text2>
            String temp = "";
            for (int i = 0; i < s.length() - 1; i++)
            {

                if ((s.charAt(i) == otag.charAt(0)))
                { // проверка на совпадение открывающего тега
                    temp = "";
                    if (s.charAt(i + 1) == otag.charAt(1))
                    {
                        k = i;
                        for (int j = 0; j < otag.length(); j++)
                        {
                            if ((s.charAt(i) != otag.charAt(j)))
                            {
                                i = k;
                                temp = "";
                                break;
                            } else
                            {
                                temp += s.charAt(i);
                                i++;
                            }
                        }
                        if (temp.equals(otag) && (i == k + temp.length()))
                        {
                            if (s.charAt(i) == ' ') {
                                temp += s.charAt(i);
                            } else if (s.charAt(i) == '>') {
                                temp += s.charAt(i);
                            }
                        }

                    } else if (s.charAt(i + 1) == ctag.charAt(1))
                    {
                        k = i;
                        for (int j = 0; j < ctag.length(); j++)
                        {
                            if ((s.charAt(i) != ctag.charAt(j)))
                            {
                                i = k;
                                temp = "";
                                break;
                            } else
                            {
                                temp += s.charAt(i);
                                i++;
                            }
                        }
                    }

                    if (temp.equals(otag + ">") || temp.equals(otag + " >"))
                    { // добавление начального индекса для substring

                        start = i - otag.length() - w;
                        stLst.add(start);
                        enLst.add(null);
                        temp = "";
                        w = 0;
                        i--;

                    } else if (temp.equals(ctag))
                    { // добавление конечного индекса для substring
                        end = i;
                        for (int j = enLst.size() - 1; j >= 0; j--) {
                            if (enLst.get(j) == null)
                            {
                                enLst.set(j, end);
                                break;
                            }
                        }
                        temp = "";
                        i--;
                    }

                } else if ('>' == s.charAt(i) && temp.equals("<" + tag + " ")) {
                    start = k;
                    stLst.add(start);
                    enLst.add(null);
                }

            }

            // вывод тегов в консоль:
            int x = enLst.get(enLst.size() - 1);
            for (int i = 0; i < stLst.size(); i++)
            {
                if (i != stLst.size() - 1)
                {
                    System.out.println(s.substring(stLst.get(i), enLst.get(i)));
                } else if (i == stLst.size() - 1)
                {
                    if (x < s.length())
                    {
                        System.out.println(s.substring(stLst.get(i), enLst.get(i)));
                    } else if (x == s.length())
                    {
                        System.out.println(s.substring(stLst.get(i)));
                    }
                }
            }

        }
        catch (IOException e)
        {
            System.out.println("Error");
        }

    }
}
