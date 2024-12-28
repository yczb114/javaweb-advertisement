package com.example.news.dao;

import com.example.news.data.News;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NewsDao extends ConnectionDao{
    //展示全部的新闻
    ArrayList<News> getAllNews();
    //按照ID查找新闻
    //用于查看新闻的详细信息
    News getNewsById(int id);
}
