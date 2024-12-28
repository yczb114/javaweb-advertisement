package com.example.shop.DAO.impl;

import com.example.shop.DAO.CommodityDao;
import com.example.shop.data.Commodity;
import com.example.shop.data.User;
import com.example.shop.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommodityDaoImpl implements CommodityDao {
    @Override
    public Commodity findByname(String str) {
        Connection connection = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from commodities where name = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, str);
            rs = pstmt.executeQuery();
            Commodity commodity=null;
            if (rs.next()) {
                int cid=rs.getInt("cid");
                String name=rs.getString("name");
                float price=rs.getFloat("price");
                String path=rs.getString("path");
                String tag= rs.getString("tag");
                commodity=new Commodity(cid,name,price,path,tag);
            }
            return commodity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.closeAll(connection, pstmt, rs);
        }
    }

    @Override
    public Commodity findByid(int id) {
        Connection connection = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from commodities where cid = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            Commodity commodity=null;
            if (rs.next()) {
                int cid=rs.getInt("cid");
                String name=rs.getString("name");
                float price=rs.getFloat("price");
                String path=rs.getString("path");
                String tag= rs.getString("tag");
                commodity=new Commodity(cid,name,price,path,tag);
            }
            return commodity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DButil.closeAll(connection, pstmt, rs);
        }
    }

    @Override
    public ArrayList<Commodity> findBytag(String tag) {
        Connection connection= getConnection();
        PreparedStatement pstmt=null;
        ResultSet rs = null;
        ArrayList<Commodity> commodities=new ArrayList<>();
        int cid;
        String name;
        float price;
        String path;
        try {
            String sql="select * from commodities where tag = ?";
            pstmt=connection.prepareStatement(sql);
            pstmt.setString(1,tag);
            rs=pstmt.executeQuery();
            Commodity commodity=null;
            while (rs.next()){
                cid=rs.getInt("cid");
                name=rs.getString("name");
                price=rs.getFloat("price");
                path=rs.getString("path");
                tag= rs.getString("tag");
                commodity=new Commodity(cid,name,price,path,tag);
                commodities.add(commodity);
            }
            return commodities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButil.closeAll(connection,pstmt,rs);
        }
    }

    @Override
    public ArrayList<Commodity> findall() {
        Connection connection= getConnection();
        PreparedStatement pstmt=null;
        ResultSet rs = null;
        ArrayList<Commodity> commodities=new ArrayList<>();
        int cid;
        String name;
        float price;
        String path;
        String tag;
        try {
            String sql="select * from commodities";
            pstmt=connection.prepareStatement(sql);
            rs=pstmt.executeQuery();
            Commodity commodity=null;
            while (rs.next()){
                cid=rs.getInt("cid");
                name=rs.getString("name");
                price=rs.getFloat("price");
                path=rs.getString("path");
                tag=rs.getString("tag");
                commodity=new Commodity(cid,name,price,path,tag);
                commodities.add(commodity);
            }
            return commodities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButil.closeAll(connection,pstmt,rs);
        }
    }
}
