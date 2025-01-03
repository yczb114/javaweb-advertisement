package com.example.shop.DAO.impl;

import com.example.shop.DAO.UserDao;
import com.example.shop.data.User;
import com.example.shop.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    @Override
    public int addUser(User user) {
        Connection connection= getConnection();
        PreparedStatement pstmt=null;
        try {
            String sql="insert into users(username,password)values(?,?)";
            pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            int row=pstmt.executeUpdate();
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButil.closeAll(connection,pstmt,null);
        }
    }

    @Override
    public User findBystr(String str) {
        Connection connection = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from users where username = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, str);
            rs = pstmt.executeQuery();
            User user = null;
            if (rs.next()) {
                int id = rs.getInt("id");
                String cart= rs.getString("cart");
                String username = rs.getString("username");
                String password = rs.getString("password");
                user = new User(id, username, password, cart);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.closeAll(connection, pstmt, rs);
        }
    }

    @Override
    public ArrayList<User> findall() {
        ArrayList<User> users = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from users";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            User user = null;
            while (rs.next()) {
                int id = rs.getInt("id");
                String cart= rs.getString("cart");
                String username = rs.getString("username");
                String password = rs.getString("password");
                user = new User(id, username, password, cart);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.closeAll(connection, pstmt, rs);
        }
    }

    @Override
    public void updateCart(String username,String cart) {
        Connection connection = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "update users set cart = ? where username = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,cart);
            pstmt.setString(2,username);
            int rows= pstmt.executeUpdate();
            System.out.println("更新了"+rows+"行");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            DButil.closeAll(connection, pstmt, rs);
        }
    }
}
