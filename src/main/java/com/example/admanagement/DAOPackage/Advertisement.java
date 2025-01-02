package com.example.admanagement.DAOPackage;

import java.io.InputStream;

public class Advertisement {
    private String adTitle;
    private String adContent;
    private InputStream adphoto;  // 图片是二进制流
    private String adName;
    private String adTag;
    private String base64Photo;  // 用于存储 Base64 编码后的图片
    private String adclick;
    private int adsend;
    private int adid;

    public Advertisement(String adTitle, String adContent, InputStream adPhoto, String advertiserName) {
        this.adTitle = adTitle;
        this.adContent = adContent;
        this.adphoto = adPhoto;
        this.adName = advertiserName;
    }

    public Advertisement(String adTitle, String adTag, String adName, InputStream adphoto, String adContent) {
        this.adTitle = adTitle;
        this.adTag = adTag;
        this.adName = adName;
        this.adphoto = adphoto;
        this.adContent = adContent;
    }

    public int getAdid() {
        return adid;
    }

    public void setAdid(int adid) {
        this.adid = adid;
    }

    public int getAdsend() {
        return adsend;
    }

    public void setAdsend(int adsend) {
        this.adsend = adsend;
    }

    public String getAdclick() {
        return adclick;
    }

    public void setAdclick(String adclick) {
        this.adclick = adclick;
    }

    public String getAdTag() {
        return adTag;
    }

    public void setAdTag(String adTag) {
        this.adTag = adTag;
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
