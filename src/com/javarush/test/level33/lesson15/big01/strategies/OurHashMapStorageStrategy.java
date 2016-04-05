package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by
 * Polurival on 05.04.2016.
 */
public class OurHashMapStorageStrategy implements StorageStrategy
{
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    int hash(Long k)
    {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    int indexFor(int hash, int length)
    {
        return hash & (length - 1);
    }

    Entry getEntry(Long key)
    {
        int hash = (key == null) ? 0 : hash((long) key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next)
        {
            Long k = null;
            if (e.hash == hash &&
                    ((k = e.key).equals(key)) || (key != null && key.equals(k)))
            {
                return e;
            }
        }
        return null;
    }

    void resize(int newCapacity)
    {
        int MAXIMUM_CAPACITY = 1 << 30;
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY)
        {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    void transfer(Entry[] newTable)
    {
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++)
        {
            Entry e = src[j];
            if (e != null)
            {
                src[j] = null;
                do
                {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                }
                while (e != null);
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex)
    {
        createEntry(hash, key, value, bucketIndex);
        if (size >= threshold)
        {
            resize(2 * table.length);
        }
    }

    void createEntry(int hash, Long key, String value, int bucketIndex)
    {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }

    @Override
    public boolean containsKey(Long key)
    {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value)
    {
        Entry[] tab;
        if ((tab = table) != null && size > 0)
        {
            for (int i = 0; i < tab.length; ++i)
            {
                for (Entry e = tab[i]; e != null; e = e.next)
                {
                    if (value != null && value.equals(e.value))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value)
    {
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        for (Entry e = table[i]; e != null; e = e.next)
        {
            if (e.hash == hash && (e.key.equals(key)))
            {
                e.value = value;
            }
        }
        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value)
    {
        for (int i = 0; i < table.length; i++)
        {
            if (value.equals(table[i].getValue()))
            {
                return table[i].getKey();
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key)
    {
        int hash = hash((long) key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next)
        {
            if (e.hash == hash && e.key.equals(key))
            {
                return e.value;
            }
        }
        return null;
    }
}
