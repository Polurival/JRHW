package com.javarush.test.level33.lesson15.big01.strategies;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by
 * Polurival on 05.04.2016.
 */
public class HashMapStorageStrategy implements StorageStrategy
{
    private HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key)
    {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value)
    {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value)
    {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value)
    {
        for (Map.Entry<Long, String> entry : data.entrySet())
        {
            if (entry.getValue().equals(value))
            {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key)
    {
        return data.get(key);
    }
}
