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

public class Solution_old
{
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String file = reader.readLine();

            //String tag = args[0];
            String tag = "span";
            String ctag = "/" + tag;
            StringBuilder sb = new StringBuilder();

            FileReader fr = new FileReader(file);
            while (true)
            {
                int i = fr.read();
                if (i == -1) break;
                String c = ((char) i) + "";
                if (!("\r".equals(c) || "\n".equals(c)))
                    sb.append(c);
            }
            String s = sb.toString();
            //System.out.println(s);

            reader.close();
            fr.close();


        ArrayList<Integer> stLst = new ArrayList<>();
        ArrayList<Integer> enLst = new ArrayList<>();
        ArrayList<Integer> revEnLst = new ArrayList<>();
        int inner = 0; // отображает степень вложенности тега
        int start;
        int end;
        int argS = 0;
        int argE = 0;
        String temp = "";
        for (int i = 0; i < s.length(); i++) {

            if ((' ' == s.charAt(i) || '>' == s.charAt(i)) && temp.equals(ctag)
                    && '<' == s.charAt(i - argE - 1)) { // добавление конечного индекса для substring
                end = i + 1;
                if (inner > 1) {
                    revEnLst.add(end);
                } else {
                    enLst.add(end);
                }
                argE = 0;
                temp = "";

            } else if ((s.charAt(i) == ctag.charAt(argE))) { // проверка на совпадение закрывающего тега
                temp += s.charAt(i);
                if (argE < ctag.length())
                    argE++;

            } else if ((' ' == s.charAt(i) || '>' == s.charAt(i)) && temp.equals(tag)
                        && '<' == s.charAt(i - argS - 1)) { // добавление начального индекса для substring

                    if (!revEnLst.isEmpty()) {
                        for (int j = revEnLst.size() - 1; j >= 0; j--)
                        {
                            enLst.add(revEnLst.get(j));
                        }
                        revEnLst.clear();
                    }

                    start = i - argS - 1;
                    stLst.add(start);
                    inner = stLst.size() - enLst.size();
                    argS = 0;
                    temp = "";

            } else if ((s.charAt(i) == tag.charAt(argS))) { // проверка на совпадение открывающего тега
                temp += s.charAt(i);
                if (argS < tag.length())
                    argS++;

            } else if (!"".equals(temp)) {
                temp = "";
                argS = 0;
                argE = 0;
            }

            if (i == s.length() - 1 && !revEnLst.isEmpty()) {
                for (int j = revEnLst.size() - 1; j >= 0; j--)
                {
                    enLst.add(revEnLst.get(j));
                }
                revEnLst.clear();
            }

        }

        for (int i = 0; i < stLst.size(); i++) {
            int x = enLst.get(enLst.size() - 1);
            if (i != stLst.size() - 1) {
                System.out.println(s.substring(stLst.get(i), enLst.get(i)));
            } else if (i == stLst.size() - 1) {
                if (x < s.length()) {
                    System.out.println(s.substring(stLst.get(i), enLst.get(i)));
                } else if (x == s.length()) {
                    System.out.println(s.substring(stLst.get(i)));
                }
            }
        }

        } catch (IOException e) {
            System.out.println("Error");
        }

    }
}
