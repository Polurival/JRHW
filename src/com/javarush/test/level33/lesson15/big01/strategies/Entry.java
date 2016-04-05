package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by
 * Polurival on 05.04.2016.
 */
public class Entry implements Serializable
{
    int hash;
    Long key;
    String value;
    Entry next;

    public Entry(int hash, Long key, String value, Entry next)
    {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Long getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return key + "=" + value;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }
}
