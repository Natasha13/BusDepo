package com.minarchenko.busdepo.model;

public class User {
    private int id;
    private String user_name;
    private String login;
    private String password;
    private String user_spesiality;

    public User(int id, String user_name, String login, String password, String user_spesiality) {
        this.id = id;
        this.user_name = user_name;
        this.login = login;
        this.password = password;
        this.user_spesiality = user_spesiality;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", user_spesiality='" + user_spesiality + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_spesiality() {
        return user_spesiality;
    }

    public void setUser_spesiality(String user_spesiality) {
        this.user_spesiality = user_spesiality;
    }
}
