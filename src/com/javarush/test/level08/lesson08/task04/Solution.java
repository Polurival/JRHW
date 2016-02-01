package com.javarush.test.level08.lesson08.task04;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static void main(String[] args)
    {
        HashMap<String, Date> map = createMap();
        removeAllSummerPeople(map);
        for (Map.Entry<String, Date> aMap : map.entrySet())
            System.out.println(aMap.getKey() + " - " + aMap.getValue());
    }

    public static HashMap<String, Date> createMap()
    {
        //напишите тут ваш код
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", new Date("JUNE 1 1980"));
        map.put("Stone", new Date("MAY 2 1988"));
        map.put("Alone", new Date("JANUARY 3 1976"));
        map.put("Pol", new Date("AUGUST 4 1972"));
        map.put("Stein", new Date("SEPTEMBER 5 1974"));
        map.put("Willow", new Date("NOVEMBER 6 1970"));
        map.put("Ali", new Date("DECEMBER 7 1987"));
        map.put("Chan", new Date("JULY 8 1986"));
        map.put("Gusev", new Date("JUNE 9 1985"));
        map.put("Kramov", new Date("APRIL 10 1984"));

        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        //напишите тут ваш код
        HashMap<String, Date> copy = new HashMap<String, Date>(map);
        for (Map.Entry<String, Date> pair : copy.entrySet())
        {
            Date d = pair.getValue();
            int m = d.getMonth();
            if (m > 4 && m < 8) map.remove(pair.getKey());

        }
    }
}
