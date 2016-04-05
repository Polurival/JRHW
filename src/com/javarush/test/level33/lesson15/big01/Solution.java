package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.OurHashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by
 * Polurival on 05.04.2016.
 */
public class Solution
{
    public static Set<Long> getIds(Shortener shortener, Set<String> strings)
    {
        Set<Long> idSet = new HashSet<>();
        for (String str : strings)
        {
            idSet.add(shortener.getId(str));
        }
        return idSet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys)
    {
        Set<String> urlSet = new HashSet<>();
        for (Long id : keys)
        {
            urlSet.add(shortener.getString(id));
        }
        return urlSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber)
    {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Shortener shortener = new Shortener(strategy);

        Set<String> randomStrings = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++)
        {
            randomStrings.add(Helper.generateRandomString());
        }

        long startGetIdsTime = new Date().getTime();
        Set<Long> ids = getIds(shortener, randomStrings);
        long endGetIdsTime = new Date().getTime();
        Helper.printMessage(String.valueOf(endGetIdsTime - startGetIdsTime));

        long startGetStringsTime = new Date().getTime();
        Set<String> strings = getStrings(shortener, ids);
        long endGetStringsTime = new Date().getTime();
        Helper.printMessage(String.valueOf(endGetStringsTime - startGetStringsTime));

        if (randomStrings.equals(strings))
        {
            Helper.printMessage("Тест пройден.");
        } else
        {
            Helper.printMessage("Тест не пройден.");
        }
    }

    public static void main(String[] args)
    {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
    }
}
