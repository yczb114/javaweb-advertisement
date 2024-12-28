package com.example.news.dao.Impl;

import com.example.news.dao.NewsDao;
import com.example.news.data.News;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class NewsDaoImpl implements NewsDao {
    //查找全部的新闻，用于主页面的展示
    @Override
    public ArrayList<News> getAllNews() {
        //防止数据库注入
        PreparedStatement p;
        //存放查找的新闻的List
        ArrayList<News> newsList = new ArrayList<>();
        try(//连接数据库
            Connection connection = getConnection()){
            //sql语句 查找news表中的所有内容
            String sql = "select * from news";
            p=connection.prepareStatement(sql);
            //rs存储数据库查询结果
            ResultSet rs=p.executeQuery();
            //数据库查询结果不为空时,获取新闻的各项信息，添加入newsList中
            while(rs.next()){
                int nid=rs.getInt("nid");
                String title=rs.getString("title");
                String content=rs.getString("content");
                String author=rs.getString("author");
                String date=rs.getString("date");
                ArrayList<String> tags=new ArrayList<>();
                String tag1=rs.getString("tag1");
                tags.add(tag1);
                String tag2=rs.getString("tag2");
                tags.add(tag2);
                String tag3=rs.getString("tag3");
                tags.add(tag3);
                News news=new News(nid,title,content,author,date,tags);
                newsList.add(news);
            }
            return newsList;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
    //通过nid查找新闻，用于新闻的详细内容的展示
    @Override
    public News getNewsById(int nid) {
        //防止数据库注入
        PreparedStatement p;
        //查找到的新闻
        News news = null;
        try(//连接数据库
            Connection connection = getConnection()){
            //sql语句 用于根据id查询新闻内容
            String sql = "select * from news where nid=?";
            p=connection.prepareStatement(sql);
            p.setInt(1, nid);
            ResultSet rs=p.executeQuery();
            //当存在查询结果时
            if(rs.next()){
                String title=rs.getString("title");
                String content=rs.getString("content");
                String author=rs.getString("author");
                String date=rs.getString("date");
                ArrayList<String> tags=new ArrayList<>();
                String tag1=rs.getString("tag1");
                tags.add(tag1);
                String tag2=rs.getString("tag2");
                tags.add(tag2);
                String tag3=rs.getString("tag3");
                tags.add(tag3);
                news = new News(nid,title,content,author,date,tags);
            }
            return news;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}
