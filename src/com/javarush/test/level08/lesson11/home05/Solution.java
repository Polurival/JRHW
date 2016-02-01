package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String s1 = "";

        //напишите тут ваш код
        for (int i = 1; i < s.length(); i++)
        {
            String temp1 = String.valueOf(s.charAt(i - 1));
            String temp = String.valueOf(s.charAt(i));

            if (i == 1) {
                s1 += temp1.toUpperCase();
            }
            if (temp1.equals(" ")/* && Character.isAlphabetic(temp)*/) s1 += temp.toUpperCase();
            else s1 += temp;

        }
        System.out.println(s1);

    }


}
