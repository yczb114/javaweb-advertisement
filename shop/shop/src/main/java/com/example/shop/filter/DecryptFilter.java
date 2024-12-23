package com.example.shop.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(servletNames = {"helloServlet"})
public class DecryptFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String key="1919810";
        String username=servletRequest.getParameter("username");
        String password=servletRequest.getParameter("password");
        if(username!=null){
            username=username.replaceFirst(key,"");
        }
        if(password!=null){
            password=password.replaceFirst(key,"");
        }
        servletRequest.setAttribute("username",username);
        servletRequest.setAttribute("password",password);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
