package com.javarush.test.level07.lesson09.task05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Удвой слова
1. Введи с клавиатуры 10 слов в список строк.
2. Метод doubleValues должен удваивать слова по принципу a,b,c -> a,a,b,b,c,c.
3. Используя цикл for выведи результат на экран, каждое значение с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws Exception {
        //read strings and init ArrayList list here - считать строки с консоли и объявить ArrayList list тут
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            String s = r.readLine();
            list.add(s);
        }

        ArrayList<String> result = doubleValues(list);

        //print result - вывести на экран result
        for (String aResult : result) System.out.println(aResult);
    }

    public static ArrayList<String> doubleValues(ArrayList<String> list) {
        // add your code here - добавь код тут
        int x = list.size();
        for (int i = 0; i < x; i++) {
            list.add(i, list.get(i));
            x = list.size();
            i++;
        }
        return list;
    }
}
