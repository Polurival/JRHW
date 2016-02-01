package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        int sum = 0, a;
        String str;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            str = br.readLine();
            if (str.equals("сумма")) break;
            a = Integer.parseInt(str);
            sum += a;
        }
        System.out.println(sum);
    }
}
