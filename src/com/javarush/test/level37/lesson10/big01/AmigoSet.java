package com.javarush.test.level37.lesson10.big01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by
 * Polurival on 27.04.2016.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E>
{
    private static final long serialVersionUID = 4243607099059311858L;

    private static final transient Object PRESENT = new Object();

    private transient HashMap<E, Object> map;

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

    private void writeObject(ObjectOutputStream oos)
    {
        try
        {
            oos.defaultWriteObject();

            oos.writeObject(map.keySet().size());
            for (E elem : map.keySet())
            {
                oos.writeObject(elem);
            }

            oos.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
            oos.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream ois)
    {
        try
        {
            ois.defaultReadObject();

            Set<E> set = new HashSet<>();
            int size = (int) ois.readObject();
            for (int i = 0; i < size; i++)
            {
                set.add((E) ois.readObject());
            }

            int capacity = (int) ois.readObject();
            float loadFactor = (float) ois.readObject();
            map = new HashMap<>(capacity, loadFactor);
            for (E elem : set)
            {
                map.put(elem, PRESENT);
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
