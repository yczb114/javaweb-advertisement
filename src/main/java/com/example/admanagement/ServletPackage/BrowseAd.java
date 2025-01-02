package com.example.admanagement.ServletPackage;


import com.example.admanagement.DAOPackage.Advertisement;
import com.example.admanagement.DAOPackage.AdvertiserDaoImpl;
import com.example.admanagement.DAOPackage.InternetWebmaster;
import com.example.admanagement.DAOPackage.InternetWebmasterDaoImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="BrowseAd",value = "/BrowseAd-servlet")
public class BrowseAd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BrowseAd");
        AdvertiserDaoImpl advertiserImpl = new AdvertiserDaoImpl();
        List<Advertisement> ads= advertiserImpl.getAllAdvertisement();
        for(Advertisement ad:ads){
            if (ad.getphoto() != null) {
                byte[] photoData = ad.getphoto().readAllBytes();
                String base64Photo = java.util.Base64.getEncoder().encodeToString(photoData);
                ad.setBase64Photo(base64Photo);  // 设置 Base64 编码后的图片
            }
        }
        InternetWebmasterDaoImpl internetWebmasterImpl = new InternetWebmasterDaoImpl();
        InternetWebmaster internetWebmaster=
        internetWebmasterImpl.findInternetWebmasterByName((String)req.getAttribute("siteName"));
        double income=internetWebmaster.getAdclick()*0.5;
        req.setAttribute("ads", ads);
        req.setAttribute("income", income);
        RequestDispatcher dispatcher = req.getRequestDispatcher("./ShowPage/InternetWebmasterPage.jsp");
        dispatcher.forward(req, resp);


    }
}
