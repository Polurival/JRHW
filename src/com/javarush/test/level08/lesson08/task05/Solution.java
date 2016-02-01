package com.javarush.test.level08.lesson08.task05;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<String, String>();
        map.put("Польщиков", "Юрий");
        map.put("Сидоров", "Юрий");
        map.put("Петров", "Николай");
        map.put("Васин", "Геннадий");
        map.put("Иванов", "Лева");
        map.put("Кузьмин", "Савелий");
        map.put("Капустин", "Александр");
        map.put("Ростов", "Михаил");
        map.put("Леонов", "Иван");
        map.put("Тэн", "Мухаммед");
        return (HashMap<String, String>) map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        //напишите тут ваш код
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (String value : copy.values())
            if (Collections.frequency(copy.values(), value) > 1)
                removeItemFromMapByValue(map, value);

    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
