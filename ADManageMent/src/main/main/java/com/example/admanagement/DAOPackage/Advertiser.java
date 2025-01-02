package com.example.admanagement.DAOPackage;

public class Advertiser {
    private String advertiserName;
    private String advertiserEmail;
    private String advertiserPassword;

    public String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
    }

    public String getAdvertiserEmail() {
        return advertiserEmail;
    }

    public void setAdvertiserEmail(String advertiserEmail) {
        this.advertiserEmail = advertiserEmail;
    }

    public String getAdvertiserPassword() {
        return advertiserPassword;
    }

    public void setAdvertiserPassword(String advertiserPassword) {
        this.advertiserPassword = advertiserPassword;
    }
    public Advertiser(String advertiserName, String advertiserEmail, String advertiserPassword) {
        this.advertiserName = advertiserName;
        this.advertiserEmail = advertiserEmail;
        this.advertiserPassword = advertiserPassword;
    }
    public Advertiser() {}
}
