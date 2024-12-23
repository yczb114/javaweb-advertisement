package com.example.shop;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.example.shop.service.Service.Login;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String button=request.getParameter("button");
        String username=(String) request.getAttribute("username");
        String password=(String) request.getAttribute("password");
        System.out.println(username+"\n"+password);
        String remember=request.getParameter("remember");
        Cookie usernameCookie=new Cookie("username",username);
        Cookie passwordCookie=new Cookie("password",password);
        HttpSession session=request.getSession();
        String warning=null;
        if(button==null){
            RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
        }
        if(button.equals("login")){
            if(remember!=null){
                usernameCookie.setMaxAge(60*60*24);
                passwordCookie.setMaxAge(60*60*24);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }else {
                usernameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }
            if(username.isEmpty()){
                warning="用户名为空";
                request.setAttribute("warning",warning);
                RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request,response);
            }
            if(!Login(username,password)){
                warning="登录失败，请检查账号密码";
                request.setAttribute("warning",warning);
                RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request,response);
            }
            session.setAttribute("username",username);
            session.setAttribute("visitor",false);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/shop.jsp");
            dispatcher.forward(request,response);
        }
        if(button.equals("register")){
            RequestDispatcher dispatcher=request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        doGet(req, resp);
    }
}