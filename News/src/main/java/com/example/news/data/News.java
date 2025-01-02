package com.example.news.data;

import java.util.ArrayList;

public class News {
    private int nid;
    private String title;
    private String author;
    private String content;
    private String date;
    private String tag;

    public News(int nid,String title, String author, String content, String date,String tag) {
        this.nid = nid;
        this.title = title;
        this.author = author;
        this.content = content;
        this.date = date;
        this.tag = tag;
    }
    public News(){}

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public int getNid() {return nid;}

    public String getTag() {return tag;}
}
