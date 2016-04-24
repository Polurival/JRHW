package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * Polurival on 24.04.2016.
 */
public class FakeModel implements Model
{
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData()
    {
        return modelData;
    }

    @Override
    public void loadUsers()
    {
        List<User> users = new ArrayList<>();
        users.add(new User("A", 1, 1));
        users.add(new User("B", 2, 1));
        modelData.setUsers(users);
    }

    @Override
    public void loadDeletedUsers()
    {
        throw new UnsupportedOperationException();
    }
}
