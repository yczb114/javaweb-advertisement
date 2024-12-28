package com.example.news.data;

import lombok.Data;

import java.util.ArrayList;

@Data
public class News {
    int nid;
    private String title;
    private String author;
    private String content;
    private String date;
    ArrayList<String> tags;

    public News(int nid,String title, String author, String content, String date, ArrayList<String> tags) {
        this.nid = nid;
        this.title = title;
        this.author = author;
        this.content = content;
        this.date = date;
        this.tags = tags;
    }
    public News(){}
}
