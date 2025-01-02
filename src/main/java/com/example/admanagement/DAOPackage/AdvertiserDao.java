package com.example.admanagement.DAOPackage;

import jakarta.servlet.http.Part;

import java.io.InputStream;
import java.util.List;

public interface AdvertiserDao extends DAO {
    public abstract void addAdvertiser(String advertiserName, String advertiserEmail, String advertiserPassword);
    public  abstract Advertiser searchAdvertiserByName(String advertiserName);
    void addAdvertisement(String advertiserName, String adTitle, String adContent, InputStream adphoto,String adTag);

    // 查询广告信息
    List<Advertisement> getAllAdvertisementByadName(String adName);
    List<Advertisement> getAllAdvertisement();

    void updateAdsend(int newadsend, int adid);

    void updateAdclick(int newclick, int adid);
}
