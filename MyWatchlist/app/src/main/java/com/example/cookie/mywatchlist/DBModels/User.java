package com.example.cookie.mywatchlist.DBModels;

public class User {
    public Long _id;
    private String name;
    private String password;

    public User() {

    }

    public User(String name, String password) {
        this.setName(name);
        this.setPassword(password);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
