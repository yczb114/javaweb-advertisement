package com.example.shop.service;

import com.example.shop.DAO.impl.UserDaoImpl;
import com.example.shop.data.User;

public class Service {
    public static boolean Login(String name,String password){
        UserDaoImpl userDao=new UserDaoImpl();
        User user=null;
        user=userDao.findBystr(name);
        if(user==null){
            return false;
        }
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public static boolean Register(String name,String password){
        User user=null;
        UserDaoImpl userDao=new UserDaoImpl();
        user=userDao.findBystr(name);
        if(user!=null){
            return false;
        }
        user=new User();
        user.setUsername(name);
        user.setPassword(password);
        userDao.addUser(user);
        return true;
    }
}
