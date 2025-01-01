package com.example.admanagement.ServletPackage;

import com.example.admanagement.DAOPackage.Advertisement;
import com.example.admanagement.DAOPackage.AdvertiserDaoImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@WebServlet(name = "SubmitServlet", value = "/SubmitAd-servlet")
public class SubmitAdvertisementServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String adName = (String) session.getAttribute("adName");
        request.setAttribute("adName", adName);
        System.out.println(adName);

        AdvertiserDaoImpl advertiserImpl = new AdvertiserDaoImpl();
        List<Advertisement> ads = advertiserImpl.getAllAdvertisementByadName(adName);

        if (ads != null) {
            // 处理图片转换为 Base64 字符串
            for (Advertisement ad : ads) {
                if (ad.getphoto() != null) {
                    byte[] photoData = ad.getphoto().readAllBytes();
                    String base64Photo = java.util.Base64.getEncoder().encodeToString(photoData);
                    ad.setBase64Photo(base64Photo);  // 设置 Base64 编码后的图片
                }
            }
        }

        request.setAttribute("ads", ads);  // 将广告数据传递到 JSP

        // 转发到JSP页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("./ShowPage/SubmitAdvertisementPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adTitle = request.getParameter("adTitle");
        String adContent = request.getParameter("adContent");
        Part adPhoto = request.getPart("adphoto");
        String adName = request.getParameter("adName");
        String adTag= request.getParameter("adTag");
        InputStream fileContent = adPhoto.getInputStream();
        AdvertiserDaoImpl advertiserImpl = new AdvertiserDaoImpl();
        advertiserImpl.addAdvertisement(adName, adTitle, adContent, fileContent,adTag);

        // 重定向到同一个 Servlet
        response.sendRedirect("SubmitAd-servlet");
    }
}
