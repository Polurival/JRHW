package com.javarush.test.level05.lesson12.bonus03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Написать программу, которая:
1. вводит с консоли число N > 0
2. потом вводит N чисел с консоли
3. выводит на экран максимальное из введенных N чисел.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maximum = Integer.MIN_VALUE;

        //напишите здесь ваш код
        int N = Math.abs(Integer.parseInt(reader.readLine()));
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
        {
            arr[i] = Integer.parseInt(reader.readLine());
        }
        for (int anArr : arr)
        {
            if (anArr > maximum)
            {
                maximum = anArr;
            }
        }

        System.out.println(maximum);
    }
}
