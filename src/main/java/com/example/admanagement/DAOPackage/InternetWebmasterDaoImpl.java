package com.example.admanagement.DAOPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InternetWebmasterDaoImpl implements InternetWebmasterDao {

    @Override
    public void addInternetWebmaster(String siteName,String siteEmail,String sitePassword) {
        String sql ="INSERT INTO internetwebmaster VALUES(?,?,?)";
        try(Connection con=getConnection();
        PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1, siteName);
            ps.setString(2, siteEmail);
            ps.setString(3, sitePassword);
            ps.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public InternetWebmaster findInternetWebmasterByName(String name) {
        String sql = "SELECT * FROM internetwebmaster WHERE siteName = ?";
        try(Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement(sql);){
            ps.setString(1, name);
            ResultSet rs=ps.executeQuery();
            InternetWebmaster internetWebmaster=new InternetWebmaster();

            if(rs.next()) {
                internetWebmaster.setInternetWebmasterName(rs.getString("siteName"));
                internetWebmaster.setInternetWebmasterEmail(rs.getString("siteEmail"));
                internetWebmaster.setInternetWebmasterPassword(rs.getString("sitePassword"));
                System.out.println(internetWebmaster.getInternetWebmasterEmail());
                return internetWebmaster;
            }else return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
