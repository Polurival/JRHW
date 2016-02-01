package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n1 = Integer.parseInt(reader.readLine());
        int n2 = Integer.parseInt(reader.readLine());

        int max = n1 > n2 ? n1 : n2;
        int min;
        if (max == n1) min = n2; else min = n1;
        int i = max;
        while (true)
        {
            if ((max % i == 0) && (min % i == 0))
            {
                System.out.println(i);
                break;
            }
            i--;
        }
    }
}
