package com.example.news.dao.Impl;

import com.example.news.dao.UserDao;
import com.example.news.data.User;
import jdk.jfr.Registered;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Registered
public class UserDaoImpl implements UserDao {
    //添加新用户到数据库
    @Override
    public boolean insertUser(User user) {
        //防止数据库注入
        PreparedStatement p;
        try(//连接数据库
            Connection connection = getConnection()){
            //sql语句 对user表进行插入
            String sql = "insert into user values(?,?)";
            p=connection.prepareStatement(sql);
            //数据库表中顺序为用户名，密码
            p.setString(1,user.getUsername());
            p.setString(2,user.getPassword());
            //返回数据库是否成功执行语句
            return p.execute();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
    //返回用户输入的用户名在数据库中的查询结果
    @Override
    public User selectUserByUsername(String username) {
        //防止数据库注入
        PreparedStatement p;
        //存放结果的User类
        User user = null;
        try(//连接数据库
            Connection connection = getConnection()){
            //sql语句 查找user表中的相应用户名
            String sql = "select * from user where username=?";
            p=connection.prepareStatement(sql);
            p.setString(1,username);
            //rs存储数据库查询结果
            ResultSet rs=p.executeQuery();
            //数据库查询结果不为空时
            if(rs.next()){
                int uid = rs.getInt("uid");
                String password = rs.getString("password");
                user = new User(uid,password,username);
            }
            return user;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}
