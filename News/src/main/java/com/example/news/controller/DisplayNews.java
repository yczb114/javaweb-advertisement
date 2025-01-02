package com.example.news.controller;

import com.example.news.dao.Impl.NewsDaoImpl;
import com.example.news.data.News;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class DisplayNews {
    //转发到th模板,显示所有的新闻标题以及作者
    //初始时默认currentTag为”所有新闻“
    @GetMapping("/DisplayNews")
    public String displayNews(Model model) {
        NewsDaoImpl newsDao = new NewsDaoImpl();
        ArrayList<News> newsList = newsDao.getAllNews();
        //将得到的newsList存入model转至网页
        model.addAttribute("newsList", newsList);
        model.addAttribute("currentTag","所有新闻");
        return "DisplayNews";
    }
    //显示所点击新闻的详细内容
    @GetMapping("/DisplayDetails/{nid}")
    public String displayDetail(@PathVariable int nid, Model model) {
        NewsDaoImpl newsDao = new NewsDaoImpl();
        News news=newsDao.getNewsById(nid);
        model.addAttribute("news", news);
        return "DisplayDetails";
    }
    //点击新闻分类后，对有所点击的tag的新闻进行展示
    //点击“所有新闻”时，不根据tag进行搜索
    //返回currentTag，用于显示目前展示的新闻分类
    @GetMapping("/news/tag/{tag}")
    public String listNewsByTag(@PathVariable String tag, Model model) {
        NewsDaoImpl newsDao = new NewsDaoImpl();
        ArrayList<News> newsList;
        if(tag.equals("all")){
            newsList = newsDao.getAllNews();
        }else{
            newsList = newsDao.getNewsByTag(tag);
        }
        model.addAttribute("newsList", newsList);
        switch (tag){
            case "all":model.addAttribute("currentTag", "所有新闻");break;
            case "digital":model.addAttribute("currentTag", "数码");break;
            case "food":model.addAttribute("currentTag", "食品");break;
            case "sport":model.addAttribute("currentTag", "运动");break;
            case "medicine":model.addAttribute("currentTag", "医药");break;
            case "test":model.addAttribute("currentTag", "测试");break;
        }
        return "DisplayNews";
    }
    //按照关键字搜索新闻
    //设置currentTag为搜索+关键字，表示目前正在搜索
    @GetMapping("/DisplayNews/search")
    public String searchNews(HttpServletRequest request, Model model) {
        NewsDaoImpl newsDao = new NewsDaoImpl();
        ArrayList<News> newsList = newsDao.getNewsByKeywords(request.getParameter("search"));
        model.addAttribute("newsList", newsList);
        model.addAttribute("currentTag","搜索："+request.getParameter("search"));
        return "DisplayNews";
    }
}
