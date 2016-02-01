package com.javarush.test.level08.lesson11.home03;

import java.util.HashMap;
import java.util.Map;

/* Люди с одинаковыми именами и/или фамилиями
1. Создать словарь Map (<String, String>) и добавить туда 10 человек в виде «Фамилия»-«Имя».
2. Пусть среди этих 10 человек есть люди с одинаковыми именами.
3. Пусть среди этих 10 человек есть люди с одинаковыми фамилиями.
4. Вывести содержимое Map на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }

    public static Map<String, String> createPeopleList()
    {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<String, String>();
        map.put("Николаев", "Патрик1");
        map.put("Николаев", "Патрик2");
        map.put("Николаев1", "Патрик3");
        map.put("Николаев2", "Патрик4");
        map.put("Николаев3", "Патрик5");
        map.put("Николаев4", "Патрик6");
        map.put("Николаев5", "Патрик3");
        map.put("Николаев3", "Патрик7");
        map.put("Николаев6", "Патрик8");
        map.put("Николаев7", "Патрик9");

        return map;
    }

    public static void printPeopleList(Map<String, String> map)
    {
        for (Map.Entry<String, String> s : map.entrySet())
        {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }

}
