package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
[Файл 1]
строка1
строка2
строка3

[Файл 2]
строка1
строка3
строка4

[Результат - список lines]
SAME строка1
REMOVED строка2
SAME строка3
ADDED строка4
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {

        BufferedReader cr = new BufferedReader(new InputStreamReader(System.in));
        String file1 = cr.readLine();
        String file2 = cr.readLine();

        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        BufferedReader br2 = new BufferedReader(new FileReader(file2));

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        while (br1.ready()) {
            list1.add(br1.readLine());
        }
        while (br2.ready()) {
            list2.add(br2.readLine());
        }

        int j = 0;
        int i = 0;
        while (true)
        {
            if (list1.get(i).equals(list2.get(j)))
            {
                lines.add(new LineItem(Type.SAME, list1.get(i)));
                j++;
                i++;
            } else if (j < list2.size() - 1 && list1.get(i).equals(list2.get(j + 1)))
            {
                lines.add(new LineItem(Type.ADDED, list2.get(j)));
                j++;
            } else if (i < list1.size() - 1 && list1.get(i + 1).equals(list2.get(j)))
            {
                lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                i++;
            }
            else

                break;

            if (i == list1.size() && j < list2.size())
            {
                for (;j< list2.size();j++)
                    lines.add(new LineItem(Type.ADDED, list2.get(j)));

            }

            if (j == list2.size() && i < list1.size())
            {
                for (; i<list1.size(); i++)
                    lines.add(new LineItem(Type.REMOVED, list1.get(i)));

            }

            if (i == list1.size() && j == list2.size())
                break;

        }


        /*for (int i = 0; i < list1.size(); i++) {

            boolean isRemoved = true;
            boolean isAdded = false;
            String temp1 = "";

            for (int j = 0; j < list2.size(); j++) {

                if (list1.get(i).equals(list2.get(j))) {
                    lines.add(new LineItem(Type.SAME, list1.get(i)));
                    isRemoved = false;
                    break;
                }
                if (!list1.contains(list2.get(j))) {
                    temp1 = list2.get(j);
                    isAdded = true;
                }

            }

            if (isRemoved) {
                lines.add(new LineItem(Type.REMOVED, list1.get(i)));
            }
            if (isAdded) {
                lines.add(new LineItem(Type.ADDED, temp1));
            }

        }*/

        /*int start = 0;
        String temp = "";
        for (int i = 0; i < list1.size(); i++) {
            boolean isAdded = false;
            if (list2.contains(list1.get(i))) {
                lines.add(new LineItem(Type.SAME, list1.get(i)));

                for (int j = start; j < list2.size(); j++) {
                    start++;
                    if (!list1.contains(list2.get(j))) {
                        isAdded = true;
                        temp = list2.get(j);
                    }
                }

                if (isAdded) {
                    lines.add(new LineItem(Type.ADDED, temp));
                }


            } else if (!list2.contains(list1.get(i))) {
                lines.add(new LineItem(Type.REMOVED, list1.get(i)));
            }
        }*/



        for (int k = 0; k < lines.size(); k++) {
            System.out.println(lines.get(k).type + " " + lines.get(k).line);
        }

        cr.close();
        br1.close();
        br2.close();

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
