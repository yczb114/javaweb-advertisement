package com.example.shop;

import com.example.shop.DAO.impl.CommodityDaoImpl;
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
import static com.example.shop.service.Service.searchCommodities;

@WebServlet(name = "shopServlet", value = "/shop-servlet")
public class ShopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        CommodityDaoImpl commodityDao=new CommodityDaoImpl();
        if(session==null){
            RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
            return;
        }
        String button=request.getParameter("button");
        String username= (String) session.getAttribute("username");
        if(button!=null&&button.equals("cadd")){
            int Cid= Integer.parseInt(request.getParameter("Cid"));
            addCart(username,Cid);
            ArrayList<Commodity> commodities=commodityDao.findall();
            request.setAttribute("commodities",commodities);
            RequestDispatcher dispatcher= request.getRequestDispatcher("/shop.jsp");
            dispatcher.forward(request,response);
            return;
        }
        if(button!=null&&button.equals("search")){
            String text=request.getParameter("searchText");
            ArrayList<Commodity> commodities=searchCommodities(text);
            request.setAttribute("commodities",commodities);
            RequestDispatcher dispatcher= request.getRequestDispatcher("/shop.jsp");
            dispatcher.forward(request,response);
            return;
        }
        String tag=request.getParameter("tag");
        ArrayList<Commodity> commodities;
        if(tag!=null){
            if(tag.equals("all")){
                commodities=commodityDao.findall();
            }else {
                commodities=commodityDao.findBytag(tag);
            }
            request.setAttribute("commodities",commodities);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/shop.jsp");
            dispatcher.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        doGet(req, resp);
    }
}
