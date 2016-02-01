package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {

    public static int[] getNumbers(int N) {

        int[] result = null;
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            int m = (i == 1) ? 1 : (int) Math.ceil(Math.log10(i));
            int[] numbersOfN = numberSplitter(i, m);
            int sum = 0;
            for (int j = 0; j < m; j++) {
                int x = numbersOfN[j];

                // возведение в степень и сложение, пример: 3*3*3 + 7*7*7 + 0*0*0
                int tempSum = 1;
                for (int k = 0; k < m; k++) {
                    tempSum *= x;
                }
                sum += tempSum;

            }
            if (i == sum) {
                list.add(i);
            }
        }

        result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    private static int[] numberSplitter (int number, int m) {

        int x = number;
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int y = x / 10;
            x = x % 10;
            result[m - 1 - i] = x;
            x = y;
        }

        return result;
    }

    public static void main(String[] args)
    {

        long s = System.currentTimeMillis();
        long memoryStart = Runtime.getRuntime().freeMemory();

        int[] result = getNumbers(100_000_000);

        long e = System.currentTimeMillis();
        long memoryEnd = Runtime.getRuntime().freeMemory();

        System.out.println((double) (e - s)/1000);
        System.out.println((memoryStart - memoryEnd) / 1048576);

        System.out.println(Arrays.toString(result));

    }
}
