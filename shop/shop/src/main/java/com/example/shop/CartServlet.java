package com.example.shop;

import com.example.shop.DAO.impl.CommodityDaoImpl;
import com.example.shop.DAO.impl.UserDaoImpl;
import com.example.shop.data.Commodity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.shop.service.Service.addCart;

@WebServlet(name = "cartServlet", value = "/cart-servlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        CommodityDaoImpl commodityDao=new CommodityDaoImpl();
        if(session==null){
            RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
            return;
        }
        String username= (String) session.getAttribute("username");
        int Cid= Integer.parseInt(request.getParameter("Cid"));
        String button=request.getParameter("button");
        if(button.equals("cadd")){
            addCart(username,Cid);
            String jsp=request.getParameter("jsp");
            if (jsp.equals("shop")){
                ArrayList<Commodity> commodities=commodityDao.findall();
                request.setAttribute("commodities",commodities);
            }
            if(jsp.equals("show")){
                Commodity commodity=commodityDao.findByid(Cid);
                request.setAttribute("commodity",commodity);
            }
            RequestDispatcher dispatcher= request.getRequestDispatcher("/"+jsp+".jsp");
            dispatcher.forward(request,response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        doGet(req, resp);
    }
}
