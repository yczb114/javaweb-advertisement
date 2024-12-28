package com.example.news.data;

import lombok.Data;

@Data
public class User {
    private int uid;
    private String username;
    private String password;

    public User(int uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }
    public User() {}
}
