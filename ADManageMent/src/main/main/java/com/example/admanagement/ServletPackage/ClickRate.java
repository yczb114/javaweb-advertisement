package com.example.admanagement.ServletPackage;


import com.example.admanagement.DAOPackage.Advertisement;
import com.example.admanagement.DAOPackage.AdvertiserDaoImpl;
import com.example.admanagement.DAOPackage.InternetWebmaster;
import com.example.admanagement.DAOPackage.InternetWebmasterDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="ClickRate", value="/ClickRate-servlet")
public class ClickRate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int clickedadid=Integer.parseInt(req.getParameter("adid"));
        String siteName=req.getParameter("username");
        AdvertiserDaoImpl  advertiserImpl=new AdvertiserDaoImpl();
        List<Advertisement> ads=
        advertiserImpl.getAllAdvertisement();

        for(Advertisement ad:ads){
            if(ad.getAdid()==clickedadid){
                advertiserImpl.updateAdclick(ad.getAdclick()+1,clickedadid);
            }
        }

        InternetWebmasterDaoImpl internetWebmasterImpl=new InternetWebmasterDaoImpl();
        InternetWebmaster internetWebmaster=
        internetWebmasterImpl.findInternetWebmasterByName(siteName);
        internetWebmaster.setAdclick(internetWebmaster.getAdclick()+1);
        internetWebmasterImpl.updateadclick(siteName,internetWebmaster.getAdclick());




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
