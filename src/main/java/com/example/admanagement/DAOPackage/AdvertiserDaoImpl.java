package com.example.admanagement.DAOPackage;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void addAdvertisement(String advertiserName, String adTitle, String adContent, InputStream adphoto,String adTag) {
        String sql = "INSERT INTO adcontent(adTitle, adContent, adPhoto, adName,adtag) VALUES (?, ?, ?, ?,?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, adTitle);
            ps.setString(2, adContent);
            ps.setBinaryStream(3, adphoto);
            ps.setString(4, advertiserName);
            ps.setString(5, adTag);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
// 查询广告信息
@Override
public List<Advertisement> getAllAdvertisementByadName(String adName) {
    List<Advertisement> ad = new ArrayList<>();
    System.out.println(adName+"111");
    String sql = "SELECT adTitle, adContent, adphoto, adName FROM adcontent WHERE adName = ?";
    try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, adName); // Set the adName for the query

        ResultSet rs = ps.executeQuery();

        // Process all rows in the ResultSet
        while (rs.next()) {
            String adTitle = rs.getString("adTitle");
            String adContent = rs.getString("adContent");
            InputStream adphoto = rs.getBinaryStream("adphoto");
            String advertiserName = rs.getString("adName");

            // Create an Advertisement object and add it to the list
            Advertisement advertisement = new Advertisement(adTitle, adContent, adphoto, advertiserName);
            ad.add(advertisement);
        }

        // Return the list of advertisements
        return ad.isEmpty() ? null : ad;

    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Database query failed", e);
    } catch (Exception e) {
        throw new RuntimeException("Unexpected error", e);
    }
}

    @Override
    public List<Advertisement> getAllAdvertisement() {
       String sql="SELECT adTitle, adContent, adphoto, adName,adtag,adid FROM adcontent";
       List<Advertisement> ads = new ArrayList<>();
       try(Connection con=getConnection();
       PreparedStatement ps= con.prepareStatement(sql)) {
           ResultSet rs = ps.executeQuery();
           while (rs.next()) {

               String adTitle = rs.getString("adTitle");
               String adContent = rs.getString("adContent");
               InputStream adphoto = rs.getBinaryStream("adphoto");
               String adName = rs.getString("adName");
               String adTag = rs.getString("adtag");
               String adid = rs.getString("adid");
               Advertisement ad=new Advertisement( adTitle,
                        adTag,  adName,  adphoto,  adContent);
               ads.add(ad);
           }
           return ads;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}




