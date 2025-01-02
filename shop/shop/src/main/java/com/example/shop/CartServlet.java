package com.example.shop;

import com.example.shop.DAO.impl.CommodityDaoImpl;
import com.example.shop.DAO.impl.UserDaoImpl;
import com.example.shop.data.Commodity;
import com.example.shop.data.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.shop.service.Service.*;

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
        String button=request.getParameter("button");
        if(button.equals("plus")){
            int Cid= Integer.parseInt(request.getParameter("cid"));
            addCart(username,Cid);
        }
        if(button.equals("sub")){
            int Cid= Integer.parseInt(request.getParameter("cid"));
            subCart(username,Cid);
        }
        if(button.equals("pay")){
            request.setAttribute("message","订单已提交");
            UserDaoImpl userDao=new UserDaoImpl();
            userDao.updateCart(username,"");
        }
        if(button.equals("showCart")||button.equals("plus")||button.equals("sub")||button.equals("pay")){
            int[] carts=showCart(username);
            ArrayList<Commodity> commodities=new ArrayList<>();
            ArrayList<Integer> num=new ArrayList<>();
            if(carts!=null){
                for (int i=1;i<carts.length;i++) {
                    if(carts[i]>0){
                        Commodity commodity=commodityDao.findByid(i);
                        commodities.add(commodity);
                        num.add(carts[i]);
                    }
                }
            }
            request.setAttribute("commodities",commodities);
            request.setAttribute("num",num);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        doGet(req, resp);
    }
}
