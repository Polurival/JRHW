package com.javarush.test.level22.lesson09.task01;

import java.io.*;
import java.util.*;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример, "мор"-"ром", "трос"-"сорт"
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();

        InputStream is = new FileInputStream(file);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String[] text = fileReader.readLine().split(" ");
        System.out.println(Arrays.toString(text));

        ArrayList<String> sList = new ArrayList<>();

        for (String word : text)
        {
            sList.add(word);
        }

        ArrayList<String> tempList = new ArrayList<>();
        tempList.addAll(sList);
        for (String word : sList) {
            String rWord = new StringBuilder(word).reverse().toString();
            if (tempList.contains(rWord) && !Objects.equals(word, rWord)) {
                Pair pair = new Pair();
                pair.first = word;
                pair.second = rWord;

                result.add(pair);

                tempList.remove(word);
                tempList.remove(rWord);
            }
        }

        for (Pair pair : result) {
            System.out.println(pair);
        }


    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
