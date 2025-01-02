package com.example.admanagement.DAOPackage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminCheck implements AdminDao{
    @Override
    public boolean checkAdmin(String adminName, int adminPassword) {
        String sql = "select administratorPassword from administrator where administratorName=?  ";
        try (Connection con=getConnection();PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1, adminName);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                if(rs.getInt("administratorPassword")==adminPassword){}
                return true;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    @Override
    public List<InternetWebmaster> getAllWebmaster(){
        String sql = "select * from internetwebmaster";
        List<InternetWebmaster> internetWebmasterList=new ArrayList<>();
        InternetWebmaster internetWebmaster=new InternetWebmaster();
        try (Connection con=getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                internetWebmaster.setInternetWebmasterName(rs.getString("siteName"));
                internetWebmaster.setInternetWebmasterPassword(rs.getString("sitePassword"));
                internetWebmaster.setInternetWebmasterEmail(rs.getString("siteEmail"));
                internetWebmasterList.add(internetWebmaster);
            }
            return internetWebmasterList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
