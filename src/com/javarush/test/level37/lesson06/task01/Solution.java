package com.javarush.test.level37.lesson06.task01;

/* Найти класс по описанию
1. Реализует интерфейс Map
2. Используется при работе с трэдами
3. Неблокирущая версия списка с пропусками, который адаптирован для хеш-таблицы.
Про список с пропусками читать в дополнительном материале к этой лекци
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        try
        {
            return Class.forName("java.util.concurrent.ConcurrentSkipListMap");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
