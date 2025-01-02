package com.example.news.dao;

import com.example.news.data.User;

public interface UserDao extends ConnectionDao{
    //添加新用户
    boolean insertUser(User user);
    //查找用户名
    //主要用于登录时确定用户名和密码
    User selectUserByUsername(String username);
}
