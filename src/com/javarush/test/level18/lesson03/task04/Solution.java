package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байты, которые встречаются в файле меньше всего раз.
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

class EntryComparator implements Comparator<Entry<Integer, Integer>>
{
    @Override
    public int compare(Entry<Integer, Integer> arg0, Entry<Integer, Integer> arg1) {
        // Compare the values.
        return arg0.getValue().compareTo(arg1.getValue());
    }
}

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        FileInputStream fis = new FileInputStream(file);
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        while (fis.available() > 0) {
            int b = fis.read();
            if (!hm.containsKey(b)) {
                hm.put(b, 1);
            } else {
                hm.put(b, hm.get(b) + 1);
            }
        }
        fis.close();

        // Copy keySet into ArrayList.
        // ... Sort with EntryComparator.
        ArrayList<Entry<Integer, Integer>> copy = new ArrayList<Entry<Integer, Integer>>();
        copy.addAll(hm.entrySet());
        Collections.sort(copy, new EntryComparator());

        // Display.
        /*for (Entry<Integer, Integer> e : copy) {
            System.out.println(e.getKey() + "..." + e.getValue());
        }*/
        String s = "";
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < copy.size(); i++) {
            Entry<Integer, Integer> ent = copy.get(i);
            if (ent.getValue() <= min) {
                min = ent.getValue();
                s += (ent.getKey() + " ");
            }
        }
        System.out.println(s);

        /*Entry<Integer, Integer> ent = copy.get(0);
        System.out.println(ent.getKey() + " " + ent.getValue());*/
    }
}
