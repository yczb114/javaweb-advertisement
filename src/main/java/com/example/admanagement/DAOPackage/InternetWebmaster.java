package com.example.admanagement.DAOPackage;

public class InternetWebmaster {
    private String InternetWebmasterName;
    private String InternetWebmasterEmail;
    private String InternetWebmasterPassword;

    public String getInternetWebmasterName() {
        return InternetWebmasterName;
    }

    public void setInternetWebmasterName(String internetWebmasterName) {
        InternetWebmasterName = internetWebmasterName;
    }

    public String getInternetWebmasterPassword() {
        return InternetWebmasterPassword;
    }

    public void setInternetWebmasterPassword(String internetWebmasterPassword) {
        InternetWebmasterPassword = internetWebmasterPassword;
    }

    public String getInternetWebmasterEmail() {
        return InternetWebmasterEmail;
    }

    public void setInternetWebmasterEmail(String internetWebmasterEmail) {
        InternetWebmasterEmail = internetWebmasterEmail;
    }
    public InternetWebmaster() {

    }
    public InternetWebmaster(String InternetWebmasterName, String InternetWebmasterEmail, String InternetWebmasterPassword) {
        this.InternetWebmasterName = InternetWebmasterName;
        this.InternetWebmasterEmail = InternetWebmasterEmail;
        this.InternetWebmasterPassword = InternetWebmasterPassword;
    }
}
