package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.User;

import java.util.List;

public interface IUserDAO {
    public User create(User new_user);
    public User find(int id);
    public User find(String name);
    public List<User> findAll(String name);
    public List<User> findAll(int id);
    public List<User> findAll();
    public void update(User new_user);
    public void delete(User old_user);
}
