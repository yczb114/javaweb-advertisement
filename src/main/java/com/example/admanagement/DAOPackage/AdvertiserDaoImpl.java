package com.example.admanagement.DAOPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvertiserDaoImpl implements AdvertiserDao {


    @Override
    public void addAdvertiser(String advertiserName, String advertiserEmail, String advertiserPassword) {

            String sql = "insert into advertiser values(?,?,?)";
        try(Connection conn=getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);

        ){
            ps.setString(1, advertiserName);
            ps.setString(2, advertiserEmail);
            ps.setString(3, advertiserPassword);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Advertiser searchAdvertiserByName(String advertiserName) {
        String sql = "SELECT * FROM advertiser WHERE advertiserName LIKE ?";
        Advertiser advertiser = new Advertiser();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, advertiserName);  // Set the advertiser name as the first parameter

            ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    advertiser.setAdvertiserName(rs.getString("advertiserName"));
                    advertiser.setAdvertiserEmail(rs.getString("advertiseEmail"));
                    advertiser.setAdvertiserPassword(rs.getString("advertisePassword"));
                    return advertiser;
                }
                else return null;
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }


}
