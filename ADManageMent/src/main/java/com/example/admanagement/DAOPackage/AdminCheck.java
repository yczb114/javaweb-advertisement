package com.example.admanagement.DAOPackage;


import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminCheck implements AdminDao{
    @Override
    public boolean checkAdmin(String adminName, String adminPassword) {
        String sql = "select * from administrator where adminName=? and adminPassword=?";
        try (Connection con=getConnection();PreparedStatement ps=con.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                if(rs.getString("adminName").equals(adminName) && rs.getString("adminPassword").equals(adminPassword)){
                    return true;
                }
                return false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
