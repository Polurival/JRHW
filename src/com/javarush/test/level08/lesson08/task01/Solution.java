package com.javarush.test.level08.lesson08.task01;

import java.util.HashSet;
import java.util.Set;

/* 20 слов на букву «Л»
Создать множество строк (Set<String>), занести в него 20 слов на букву «Л».
*/

public class Solution
{
    public static HashSet<String> createSet()
    {
        Set<String> set = new HashSet<String>();
        set.add("Лебедь");
        set.add("Лира");
        set.add("Лоза");
        set.add("Лимон");
        set.add("Лимонад");
        set.add("Ложка");
        set.add("Лодка");
        set.add("Лекарь");
        set.add("Лошадь");
        set.add("Линейка");
        set.add("Лебедка");
        set.add("Латынь");
        set.add("Литий");
        set.add("Лев");
        set.add("Лесть");
        set.add("Лопасти");
        set.add("Лопух");
        set.add("Лом");
        set.add("Ливерпуль");
        set.add("Лак");
        return (HashSet<String>) set;
    }
}
