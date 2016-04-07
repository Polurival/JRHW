package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by
 * Polurival on 07.04.2016.
 */
public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids)
    {
        long startedTime = new Date().getTime();
        for (String str : strings)
        {
            ids.add(shortener.getId(str));
        }
        long endedTime = new Date().getTime();
        return endedTime - startedTime;
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings)
    {
        long startedTime = new Date().getTime();
        for (Long id : ids)
        {
            strings.add(shortener.getString(id));
        }
        long endedTime = new Date().getTime();
        return endedTime - startedTime;
    }

    @Test
    public void testHashMapStorage()
    {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++)
        {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> origIds = new HashSet<>();
        long HMGetIdsTime = getTimeForGettingIds(shortener1, origStrings, origIds);
        long HBMGetIdsTime = getTimeForGettingIds(shortener2, origStrings, origIds);
        Assert.assertTrue(HMGetIdsTime > HBMGetIdsTime);

        long HMGetStringsTime = getTimeForGettingStrings(shortener1, origIds, new HashSet<String>());
        long HBMGetStringsTime = getTimeForGettingStrings(shortener2, origIds, new HashSet<String>());
        Assert.assertEquals(HMGetStringsTime, HBMGetStringsTime, 5);
    }
}
