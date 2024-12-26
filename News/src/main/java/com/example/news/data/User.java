package com.example.news.data;

public class User {
    //用户类应有用户id，用户名，密码
    //用户名作为登录的唯一标识，不应当重复
    private int uid;
    private String username;
    private String password;

    public User(int uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }
    public User(){}
    public void setUid(int uid) {this.uid = uid;}
    public int getUid() {return uid;}
    public void setUsername(String username) {this.username = username;}
    public String getUsername() {return username;}
    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return password;}

}
