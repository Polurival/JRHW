package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("Сидоров", "Вася");
        hm.put("Сколов", "Коля");
        hm.put("Сосков", "Дима");
        hm.put("Лебедев", "Тимур");
        hm.put("Недов", "Петр");
        hm.put("Федоров", "Костя");
        hm.put("Синичкин", "Саша");
        hm.put("Петров", "Коля");
        hm.put("Серов", "Дима");
        hm.put("Невзоров", "Леша");
        return hm;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        //напишите тут ваш код
        int count = 0;
        for (Map.Entry<String, String> pair : map.entrySet())
        {
            String s = pair.getValue();
            if (s.equals(name)) count++;
        }
        return count;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String familiya)
    {
        //напишите тут ваш код
        int count = 0;
        for (Map.Entry<String, String> pair : map.entrySet())
        {
            String s = pair.getKey();
            if (s.equals(familiya)) count++;
        }
        return count;
    }
}
