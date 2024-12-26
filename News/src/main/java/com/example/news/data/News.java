package com.example.news.data;

public class News {
    //新闻有id，标题，内容，日期和发布者
    private int nid;
    private String title;
    private String content;
    private String date;
    private String author;

    public News (int nid, String title, String content, String date, String author) {
        this.nid = nid;
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
    }

    public void setNid(int nid) {this.nid = nid;}
    public int getNid() {return nid;}
    public void setTitle(String title) {this.title = title;}
    public String getTitle() {return title;}
    public void setContent(String content) {this.content = content;}
    public String getContent() {return content;}
    public void setDate(String date) {this.date = date;}
    public String getDate() {return date;}
    public void setAuthor(String author) {this.author = author;}
    public String getAuthor() {return author;}
}
