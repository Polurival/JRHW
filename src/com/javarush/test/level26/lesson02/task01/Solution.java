package com.javarush.test.level26.lesson02.task01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);

        float median;

        if (array.length % 2 == 1) {
            median = array[array.length / 2];
        } else {
            median = (array[(array.length / 2) - 1] + array[(array.length / 2)]) / 2f;
        }

        // final здесь скорее всего нужно из-за особенностей javaRush сервера.
        final double finalMedium = median;
        Arrays.sort(array, new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                int result = (int) (Math.abs(o1 - finalMedium) - Math.abs(o2 - finalMedium));
                return result == 0 ? o1 - o2 : result;
            }
        });

        return array;
    }

    public static void main(String[] args)
    {
        Integer[] a = new Integer[]{2, 5, 6, 7, 21, 1};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
