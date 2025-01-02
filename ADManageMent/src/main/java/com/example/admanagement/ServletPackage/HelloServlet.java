package com.example.admanagement.ServletPackage;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action =request.getParameter("action");

        if(action.equals("Advertisers")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("./To_login/Advertisers_login.html");
            dispatcher.forward(request, response);return;
        }
        if(action.equals("InternetWebmaster")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("./To_login/InternetWebmaster_login.html");
            dispatcher.forward(request, response);return;
        }
        if(action.equals("Administrator")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("./To_login/Administrator_login.html");
            dispatcher.forward(request, response);return;
        }
        if(action.equals("register")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("./To_login/Register.html");
            dispatcher.forward(request, response);return;
        }
    }

    public void destroy() {
    }
}