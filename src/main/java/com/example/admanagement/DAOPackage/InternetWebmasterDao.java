package com.example.admanagement.DAOPackage;

public interface InternetWebmasterDao extends DAO{
 void addInternetWebmaster(String internetWebmasterName,String siteEmail, String internetWebmasterPassword);
 InternetWebmaster findInternetWebmasterByName(String name);
}
