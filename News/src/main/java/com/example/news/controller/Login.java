package com.example.news.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Login {
    //转发到th模板
    @GetMapping("/Login")
    public String login(){return "Login";}
    //查看登录是否成功
    @PostMapping("/Login")
    public String checkLogin(Model model, HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return "Result";
    }
}
