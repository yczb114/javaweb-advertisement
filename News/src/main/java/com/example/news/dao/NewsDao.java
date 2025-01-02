package com.example.news.dao;

import com.example.news.data.News;

import java.util.ArrayList;

public interface NewsDao extends ConnectionDao{
    //展示全部的新闻
    ArrayList<News> getAllNews();
    //按照ID查找新闻
    //用于查看新闻的详细信息
    News getNewsById(int id);
    //按照标签查找新闻
    //用于按照分类查看新闻
    ArrayList<News> getNewsByTag(String tag);
    //按照关键字查找新闻
    //用于进行新闻的搜索
    ArrayList<News> getNewsByKeywords(String keywords);
}
