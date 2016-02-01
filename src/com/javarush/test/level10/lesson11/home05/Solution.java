package com.javarush.test.level10.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Количество букв
Ввести с клавиатуры 10 строчек и подсчитать в них количество различных букв (для 33 букв алфавита).  Вывести результат на экран.
Пример вывода:
а 5
б 8
в 3
г 7
…
я 9
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //алфавит
        String abc = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        char[] abcArray = abc.toCharArray();

        ArrayList<Character> alphabet = new ArrayList<Character>();
        for (int i = 0; i < abcArray.length; i++)
        {
            alphabet.add(abcArray[i]);
        }

        //ввод строк
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
        {
            String s = reader.readLine();
            list.add(s.toLowerCase());
        }

        //напишите тут ваш код
        ArrayList<Integer> counList = new ArrayList<Integer>();
        char[] cl;
        int count;
        for (int i = 0; i < alphabet.size(); i++) {
            count = 0;
            for (int j = 0; j < list.size(); j++) {
                cl = list.get(j).toCharArray();
                for (int k = 0; k < cl.length; k++) {
                    if(String.valueOf(cl[k]).equals(String.valueOf(alphabet.get(i)))) {
                        count++;
                    }
                }
            }
            counList.add(count);
        }
        for (int i = 0; i < alphabet.size(); i++) {
            System.out.println(alphabet.get(i) + " " + counList.get(i));
        }



        /*int[] numbers = new int[alphabet.size()]; //количество букв
        for (String aList : list) // для каждого введенного слова
        {
            for (int i = 0; i < aList.length(); i++) // для каждого символа в слове
            {
                for (Character anAlphabet : alphabet) // для каждого символа в словаре
                {
                    if (aList.charAt(i) == anAlphabet) // если символ в слове равен символу в словаре
                    {
                        numbers[alphabet.indexOf(anAlphabet)] += 1; // прибавляем 1ку в numbers, по индексу,
                    }                                               // равному индексу символа в alphabet
                }

            }
        }
        for (int i = 0; i < alphabet.size(); i++)
        {
            if (numbers[i] != 0) // если символ не встретился в введенных словах, то его не выводим
                System.out.println(alphabet.get(i) + " " + numbers[i]);
        }*/
    }

}
