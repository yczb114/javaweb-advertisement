package com.example.admanagement.ServletPackage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "SubmitServlet", value = "/SubmitAd-servlet")

public class SubmitAdvertisementServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String adName=(String) session.getAttribute("adName");
        String adEmail=(String) session.getAttribute("adEmail");
        request.setAttribute("adName", adName);
        request.setAttribute("adEmail", adEmail);
        //从会话得到信息，传到jsp中
        RequestDispatcher dispatcher = request.getRequestDispatcher("./ShowPage/SubmitAdvertisementPage.jsp");
        dispatcher.forward(request, response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }




}

