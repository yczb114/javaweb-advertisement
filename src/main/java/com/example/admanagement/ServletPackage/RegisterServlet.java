package com.example.admanagement.ServletPackage;

import com.example.admanagement.DAOPackage.AdvertiserDaoImpl;
import com.example.admanagement.DAOPackage.InternetWebmasterDaoImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/Register-servlet")
public class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("./To_login/Register.html");
        dispatcher.forward(request, response);return;
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取注册类型

        String registerType = request.getParameter("registerType");
        response.setContentType("text/plain");

        System.out.println(registerType);
        if (registerType == null) {
            response.getWriter().write("注册类型未指定！");
            return;
        }
        AdvertiserDaoImpl advertiserImpl = new AdvertiserDaoImpl();
        InternetWebmasterDaoImpl internetWebmasterImpl = new InternetWebmasterDaoImpl();
        if (registerType.equals("ad")) {
            // 处理广告主注册
            String adName = request.getParameter("adName");
            String adEmail = request.getParameter("adEmail");
            String adPassword = request.getParameter("adPassword");

            if (adName != null && adEmail != null && adPassword != null
                    &&!adName.isEmpty()&&!adEmail.isEmpty()
                    &&(!adPassword.isEmpty())&&(advertiserImpl.searchAdvertiserByName(adName)==null)
            ) {
                //判断，如果不存在这样的用户，则允许，否则弹出警告，直接返回登录

                advertiserImpl.addAdvertiser(adName, adEmail, adPassword);

                //保存到数据库


                //后续登录依然然需要用户名信息等，因此将信息存入session中
                request.setAttribute("adName",adName);
                request.setAttribute("adEmail",adEmail);



                response.getWriter().write("广告主注册成功！姓名: " + adName + ", 邮箱: " + adEmail);
                response.getWriter().write("\n进入网页");

                response.sendRedirect("./SubmitAd-servlet");


            }
            else{
                response.getWriter().write("广告主注册失败，已经存在这个名字的用户");

                response.sendRedirect("Register-servlet");
            }


        } else if (registerType.equals("site")) {
            // 处理网站主注册
            String siteName = request.getParameter("siteName");
            String siteEmail = request.getParameter("siteEmail");
            String sitePassword = request.getParameter("sitePassword");

            //查找是否存在此名字的互联网站长
            if((internetWebmasterImpl.findInternetWebmasterByName(siteName))==null
                &&siteName!=null&&siteEmail!=null&&sitePassword!=null
                    &&!siteName.isEmpty()&&!siteEmail.isEmpty()&&!sitePassword.isEmpty()
            ){
                internetWebmasterImpl.addInternetWebmaster(siteName, siteEmail, sitePassword);
            };//检查无误，加入这个互联网站长



            // 这里可以做进一步的处理，比如保存到数据库
            response.getWriter().write("网站主注册成功！姓名: " + siteName + ", 邮箱: " + siteEmail);
        } else {
            response.getWriter().write("未知的注册类型！");
            response.sendRedirect("Register-servlet");
        }
    }
}