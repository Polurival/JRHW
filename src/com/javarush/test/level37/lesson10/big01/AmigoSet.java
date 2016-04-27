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
        return map.keySet().iterator();
    }

    @Override
    public int size()
    {
        return map.keySet().size();
    }

    @Override
    public boolean add(E e)
    {
        map.put(e, PRESENT);
        return map.containsKey(e);
    }

    @Override
    public boolean isEmpty()
    {
        return map.keySet().isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return map.keySet().contains(o);
    }

    @Override
    public void clear()
    {
        map.keySet().clear();
    }

    @Override
    public boolean remove(Object o)
    {
        return map.keySet().remove(o);
    }

    @Override
    public Object clone()
    {
        AmigoSet<E> amigoSet = new AmigoSet<>();
        amigoSet.addAll(this);
        amigoSet.map.putAll(this.map);
        return amigoSet;
    }
}
