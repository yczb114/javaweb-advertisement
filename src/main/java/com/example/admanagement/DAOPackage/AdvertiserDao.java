package com.example.admanagement.DAOPackage;

public interface AdvertiserDao extends DAO {
    public abstract void addAdvertiser(String advertiserName, String advertiserEmail, String advertiserPassword);
    public  abstract Advertiser searchAdvertiserByName(String advertiserName);
}
