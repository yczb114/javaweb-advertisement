package com.example.shop.DAO;

import com.example.shop.data.User;

import java.util.ArrayList;

public interface UserDao extends Dao {
    public int addUser(User user);
    public User findBystr(String str);
    public ArrayList<User> findall();
}
