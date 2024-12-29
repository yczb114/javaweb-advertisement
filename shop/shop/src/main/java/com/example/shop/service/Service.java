package com.example.shop.service;

import com.example.shop.DAO.impl.CommodityDaoImpl;
import com.example.shop.DAO.impl.UserDaoImpl;
import com.example.shop.data.Commodity;
import com.example.shop.data.User;

import java.util.ArrayList;

public class Service {
    public static boolean Login(String name,String password){
        UserDaoImpl userDao=new UserDaoImpl();
        User user=null;
        user=userDao.findBystr(name);
        if(user==null){
            return false;
        }
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public static boolean Register(String name,String password){
        User user=null;
        UserDaoImpl userDao=new UserDaoImpl();
        user=userDao.findBystr(name);
        if(user!=null){
            return false;
        }
        user=new User();
        user.setUsername(name);
        user.setPassword(password);
        userDao.addUser(user);
        return true;
    }
    public static void addCart(String username,int id){
        UserDaoImpl userDao=new UserDaoImpl();
        User user=null;
        user=userDao.findBystr(username);
        String cart=user.getCart();
        if(cart==null||cart.isEmpty()){
            cart=id+"";
        }else {
            cart=cart+" "+id;
        }
        userDao.updateCart(username,cart);
    }
    public static int[] showCart(String username){
        UserDaoImpl userDao=new UserDaoImpl();
        CommodityDaoImpl commodityDao=new CommodityDaoImpl();
        User user=null;
        user=userDao.findBystr(username);
        ArrayList<Commodity> commodities=commodityDao.findall();
        int[] num=new int[commodities.size()+1];
        String cart=user.getCart();
        String[] carts=cart.split(" ");
        int[] id=new int[carts.length];
        for (int i=0;i<carts.length;i++) {
            id[i]= Integer.parseInt(carts[i]);
            num[id[i]]=num[id[i]]+1;
        }
        return num;
    }
}
