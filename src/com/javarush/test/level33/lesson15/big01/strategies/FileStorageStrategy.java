package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by
 * Polurival on 06.04.2016.
 */
public class FileStorageStrategy implements StorageStrategy
{
    private FileBucket[] table = {new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket(),
            new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket(),
            new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket(),
            new FileBucket(), new FileBucket(), new FileBucket(), new FileBucket()};
    private long bucketSizeLimit = 10000;

    public long getBucketSizeLimit()
    {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit)
    {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    int hash(Long k)
    {
        return k.hashCode();
    }

    int indexFor(int hash, int length)
    {
        return hash & (length - 1);
    }

    Entry getEntry(Long key)
    {
        int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next)
        {
            if ((e.hash == hash) && (key != null && key.equals(e.key)))
            {
                return e;
            }
        }
        return null;
    }

    public void resize(int newCapacity)
    {
        FileBucket[] newTable = new FileBucket[newCapacity];
        for (int i = 0; i < newTable.length; i++)
        {
            newTable[i] = new FileBucket();
        }
        transfer(newTable);

        for (int i = 0; i < table.length; i++)
        {
            table[i].remove();
            table[i] = null;
        }
        table = newTable;
    }

    void transfer(FileBucket[] newTable)
    {
        int newCapacity = newTable.length;
        for (int j = 0; j < table.length; j++)
        {
            for (Entry e = table[j].getEntry(); e != null; )
            {
                Entry next = e.next;
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i].getEntry();
                newTable[i].putEntry(e);
                e = next;
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex)
    {

        FileBucket bucket = table[bucketIndex];
        if ((bucket != null) && (bucket.getFileSize() > getBucketSizeLimit()))
        {
            resize(2 * table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex)
    {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
    }

    @Override
    public boolean containsKey(Long key)
    {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value)
    {
        if (value == null)
        {
            return false;
        }
        for (FileBucket bucket : table)
        {
            if (bucket == null)
            {
                continue;
            }
            for (Entry e = bucket.getEntry(); e != null; e = e.next)
            {
                if (value.equals(e.value))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value)
    {
        if (key == null)
        {
            return;
        }
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        for (Entry e = table[i].getEntry(); e != null; e = e.next)
        {
            if ((e.hash == hash) && (key.equals(e.key)))
            {
                e.value = value;
                return;
            }
        }
        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value)
    {
        if (value == null)
        {
            return null;
        }
        for (FileBucket bucket : table)
        {
            for (Entry e = bucket.getEntry(); e != null; e = e.next)
            {
                if (value.equals(e.value))
                {
                    return e.getKey();
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key)
    {
        return null == getEntry(key) ? null : getEntry(key).value;
    }
}
