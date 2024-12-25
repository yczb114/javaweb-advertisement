package com.example.shop;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.example.shop.service.Service.Register;

@WebServlet(name = "registerServlet", value = "/register-servlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String repassword=request.getParameter("repassword");
        String button=request.getParameter("button");
        HttpSession session=request.getSession(false);
        String warning=null;
        if(session==null){
            RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
            return;
        }
        if(username==null||password==null||repassword==null||button==null){
            RequestDispatcher dispatcher=request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request,response);
            return;
        }
        if(button.equals("back")){
            RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
            return;
        }
        if(username.isEmpty()){
            warning="用户名为空";
            request.setAttribute("warning",warning);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request,response);
            return;
        }
        if(password.isEmpty()){
            warning="密码为空";
            request.setAttribute("warning",warning);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request,response);
            return;
        }
        if(!password.equals(repassword)){
            warning="两次密码不相等";
            request.setAttribute("warning",warning);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request,response);
            return;
        }
        if(!Register(username,password)){
            warning="用户名重复，注册失败";
            request.setAttribute("warning",warning);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request,response);
            return;
        }
        RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        doGet(req,resp);
    }
}
