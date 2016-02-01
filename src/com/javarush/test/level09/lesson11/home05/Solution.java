package com.javarush.test.level09.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Гласные и согласные буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа должна вывести на экран две строки:
1. первая строка содержит только гласные буквы
2. вторая - только согласные буквы и знаки препинания из введённой строки.
Буквы соединять пробелом.

Пример ввода:
Мама мыла раму.
Пример вывода:
а а ы а а у
М м м л р м .
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        ArrayList<String> line1 = new ArrayList<String>();
        ArrayList<String> line2 = new ArrayList<String>();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String text = r.readLine();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (isVowel(c))
                line1.add(String.valueOf(c));
            else if (!String.valueOf(c).equals(" "))
                line2.add(String.valueOf(c));
        }
        for (String aLine : line1)
            System.out.print(aLine + " ");
        System.out.println();
        for (String aLine : line2)
            System.out.print(aLine + " ");
    }


    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    //метод проверяет, гласная ли буква
    public static boolean isVowel(char c)
    {
        c = Character.toLowerCase(c);  //приводим символ в нижний регистр - от заглавных к строчным буквам

        for (char d : vowels)   //ищем среди массива гласных
        {
            if (c == d)
                return true;
        }
        return false;
    }
}
