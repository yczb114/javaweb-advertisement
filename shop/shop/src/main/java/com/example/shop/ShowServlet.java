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

@WebServlet(name = "showServlet", value = "/show-servlet")
public class ShowServlet extends HttpServlet {
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
            Commodity commodity=commodityDao.findByid(Cid);
            request.setAttribute("commodity",commodity);
            RequestDispatcher dispatcher= request.getRequestDispatcher("/show.jsp");
            dispatcher.forward(request,response);
            return;
        }
        if(button!=null&&button.equals("back")){
            ArrayList<Commodity> commodities=commodityDao.findall();
            request.setAttribute("commodities",commodities);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/shop.jsp");
            dispatcher.forward(request,response);
            return;
        }
        int Cid= Integer.parseInt(request.getParameter("Cid"));
        Commodity commodity=commodityDao.findByid(Cid);
        request.setAttribute("commodity",commodity);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/show.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        doGet(req, resp);
    }
}
