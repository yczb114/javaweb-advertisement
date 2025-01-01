package com.example.admanagement.ServletPackage;

import com.example.admanagement.DAOPackage.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/Login-servlet")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logintype = request.getParameter("loginType");
        System.out.println(logintype);
        if(logintype.isEmpty() ||logintype==null)
            response.sendRedirect("hello-servlet");//没有登录选项
        if(logintype.equals("admin")){
            String adminName = request.getParameter("adminName");
            String adminPassword = request.getParameter("adminPassword");
            AdminCheck adminCheck = new AdminCheck();
            if (adminCheck.checkAdmin(adminName, adminPassword)) {
                RequestDispatcher rd = request.getRequestDispatcher("./ShowPage/Adminstrator.jsp");
            }//进入管理员界面


        }
        if(logintype.equals("ad")){
            String adName = request.getParameter("adName");
            String adPassword = request.getParameter("adPassword");
            AdvertiserDaoImpl advertiserImpl = new AdvertiserDaoImpl();
            Advertiser advertiser =advertiserImpl.searchAdvertiserByName(adName);
            if(advertiser==null){
                response.sendRedirect("Register-servlet");
            }

            //对比密码是否相同

            if(adPassword.equals(advertiser.getAdvertiserPassword())){
                HttpSession session = request.getSession();
                session.setAttribute("adName", adName);
                response.sendRedirect("SubmitAd-servlet");
            }//转发到广告提交页面
            else{response.sendRedirect("Login-servlet");}
        }
        if(logintype.equals("site")){

            String siteName = request.getParameter("siteName");
            String sitePassword = request.getParameter("sitePassword");
            System.out.println(siteName+sitePassword);
            InternetWebmasterDaoImpl internetWebmasterImpl = new InternetWebmasterDaoImpl();
            InternetWebmaster internetWebmaster=internetWebmasterImpl.findInternetWebmasterByName(siteName);
            System.out.println(internetWebmaster.getInternetWebmasterPassword());
            if(internetWebmaster!=null&&internetWebmaster.getInternetWebmasterPassword().equals(sitePassword)){
                response.sendRedirect("BrowseAd-servlet");

            }else{
                response.getWriter().write("有问题");

            }
        }
    }
}
