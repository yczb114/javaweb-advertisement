package com.example.admanagement.DAOPackage;

import java.io.InputStream;

public class Advertisement {
    private String adTitle;
    private String adContent;
    private InputStream adphoto;  // 图片是二进制流
    private String adName;
    private String base64Photo;  // 用于存储 Base64 编码后的图片

    public Advertisement(String adTitle, String adContent, InputStream adPhoto, String advertiserName) {
        this.adTitle = adTitle;
        this.adContent = adContent;
        this.adphoto = adPhoto;
        this.adName = advertiserName;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public String getAdContent() {
        return adContent;
    }

    public InputStream getphoto() {
        return adphoto;
    }

    public String getAdvertiserName() {
        return adName;
    }

    public String getBase64Photo() {
        return base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        this.base64Photo = base64Photo;
    }
}
