package com.javarush.test.level36.lesson04.big01.controller;

import com.javarush.test.level36.lesson04.big01.model.Model;
import com.javarush.test.level36.lesson04.big01.view.EditUserView;
import com.javarush.test.level36.lesson04.big01.view.UsersView;

/**
 * Created by
 * Polurival on 24.04.2016.
 */
public class Controller
{
    private Model model;
    private UsersView usersView;
    private EditUserView editUserView;

    public void setModel(Model model)
    {
        this.model = model;
    }

    public void setUsersView(UsersView usersView)
    {
        this.usersView = usersView;
    }

    public void setEditUserView(EditUserView editUserView)
    {
        this.editUserView = editUserView;
    }

    public void onShowAllUsers()
    {
        model.loadUsers();
        usersView.refresh(model.getModelData());
    }

    public void onShowAllDeletedUsers() {
        model.loadDeletedUsers();
        usersView.refresh(model.getModelData());
    }

    public void onOpenUserEditForm(long userId) {
        model.loadUserById(userId);
        editUserView.refresh(model.getModelData());
    }

    public void onUserDelete(long id)
    {
        model.deleteUserById(id);
        usersView.refresh(model.getModelData());
    }
}
