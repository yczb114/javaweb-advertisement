package com.example.news.controller;

import com.example.news.dao.Impl.UserDaoImpl;
import com.example.news.data.User;
import jakarta.servlet.http.HttpServletRequest;
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
    public String checkLogin(Model model, HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDaoImpl userDao = new UserDaoImpl();
        //通过userDao接口查找相应用户名
        User user = userDao.selectUserByUsername(username);
        //用户名为空时，返回用户名不存在提醒
        if(user == null){
            model.addAttribute("message","用户名不存在");
            return "Login";
        }
        //成功得到用户信息后，核对密码，根据密码匹配返回结果
        if(user.getPassword().equals(password)){
            //重新定向，不能直接渲染视图
            return "redirect:DisplayNews";
        }else{
            model.addAttribute("message","密码错误");
            return "Login";
        }
    }
}
