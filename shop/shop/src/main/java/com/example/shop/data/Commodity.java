package com.example.shop.data;

public class Commodity {
    private int Cid;
    private String name;
    private float price;
    private String path;
    private String tag;

    public Commodity(int cid, String name, float price, String path, String tag) {
        Cid = cid;
        this.name = name;
        this.price = price;
        this.path = path;
        this.tag = tag;
    }

    public Commodity(){}

    public int getCid() {
        return Cid;
    }

    public void setCid(int cid) {
        Cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
