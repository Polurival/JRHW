package com.javarush.test.level22.lesson09.task03;

import java.io.*;
import java.util.*;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();

        InputStream is = new FileInputStream(file);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String[] words = fileReader.readLine().split(" ");

        reader.close();
        is.close();
        fileReader.close();

        System.out.println(Arrays.toString(words));


        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words)
    {
        if (words == null || words.length == 0)
            return new StringBuilder();
        if ("".equals(words[0]) || words.length == 1)
            return new StringBuilder(words[0]);


        Random rnd = new Random();
        String[] cards = new String[words.length];

        for (int i = 0; i < cards.length; i++)
        {
            cards[i] = words[i];
        }

        boolean isSorted = false;

        while (!isSorted) // будет перемешивать элементы массива пока он не станет соответствовать условию задачи
        {
            for (int i = 0; i < cards.length; i++)
            {
                int position = i + rnd.nextInt(cards.length - i);
                String temp = cards[i];
                cards[i] = cards[position];
                cards[position] = temp;
            }

            for (int i = 0; i < cards.length - 1; i++)
            {
                char last = cards[i].charAt(cards[i].length() - 1);
                char first = cards[i + 1].toLowerCase().charAt(0);
                if (last == first)
                {
                    isSorted = true;
                } else
                {
                    isSorted = false;
                    break;
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.length; i++)
        {
            sb.append(cards[i]).append(" ");
        }


        return sb;


        // мой неправильный способ:
        /*// проверки входящего массива
        if (words == null || words.length == 0)
            return new StringBuilder();
        if ("".equals(words[0]) || words.length == 1)
            return new StringBuilder(words[0]);



        StringBuilder result = new StringBuilder();

        List<String> list = new ArrayList<>();
        Collections.addAll(list, words);

        ArrayList<String> tempList = new ArrayList<>();
        tempList.addAll(list);



        int lastIndex = 0; // индекс последенего символа последнего добавленного слова
        String word = null; // индекс последнего добавленного в result слова
        int pos = 0;    // индекс слова, с которого начинается очередная цепочка проверки, до тех пор,
                        // пока words.length станет равна количеству слов в sb

        for (int i = 0; i < list.size(); i++)
        {

            if (i == 0)
            {
                if (tempList.contains(list.get(i)))
                {
                    result.append(list.get(i));
                    lastIndex = list.get(i).length() - 1;
                    word = list.get(i);
                    tempList.remove(word);
                }
            }

            for (int j = 0; j < list.size(); j++)
            {
                if (tempList.contains(list.get(j)))
                {
                    assert word != null;
                    if (word.charAt(lastIndex) == list.get(j).toLowerCase().charAt(0))
                    {
                        result.append(" ").append(list.get(j));
                        lastIndex = list.get(j).length() - 1;
                        word = list.get(j);
                        tempList.remove(word);
                        break;
                    }
                }

            }
        }

        return result;*/

    }
}
