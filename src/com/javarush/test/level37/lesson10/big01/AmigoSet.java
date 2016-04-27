package com.javarush.test.level37.lesson10.big01;

import java.io.Serializable;
import java.util.*;

/**
 * Created by
 * Polurival on 27.04.2016.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E>
{
    private static final Object PRESENT = new Object();

    private HashMap<E, Object> map;

    public AmigoSet()
    {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection)
    {
        int capacity = 16 > (collection.size()/.75f) ? 16 : (int) (collection.size() / .75f);
        map = new HashMap<>(capacity);
        for (E elem : collection)
        {
            map.put(elem, PRESENT);
        }
    }

    @Override
    public Iterator<E> iterator()
    {
        return null;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public boolean add(E e)
    {
        map.put(e, PRESENT);
        return map.containsKey(e);
    }
}
