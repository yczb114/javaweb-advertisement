package com.example.shop.DAO;

import com.example.shop.data.Commodity;

import java.util.ArrayList;

public interface CommodityDao extends Dao{
    public Commodity findBystr(String str);
    public Commodity findByid(int id);
    public ArrayList<Commodity> findBytag(String tag);

    public ArrayList<Commodity> findall();
}
