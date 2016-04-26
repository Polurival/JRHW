package com.javarush.test.level37.lesson04.task01;

import java.util.ArrayList;
import java.util.Iterator;

/* Круговой итератор
Класс Solution наследуется от ArrayList.
Напишите свой класс RoundIterator внутри Solution, который будет итератором для списка Solution.
Итератор должен ходить по кругу по всем элементам.
В остальном поведение должно быть идентичным текущему итератору.
*/
public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator()
    {
        return new RoundIterator<>();
    }

    public class RoundIterator<T> implements Iterator<T>
    {
        int cursor = 0;

        @Override
        public boolean hasNext()
        {
            return true;
        }

        @Override
        public T next()
        {
            if (cursor == Solution.super.size())
            {
                cursor = 0;
            }
            return (T) Solution.super.get(cursor++);
        }

        @Override
        public void remove()
        {
        }
    }
}
