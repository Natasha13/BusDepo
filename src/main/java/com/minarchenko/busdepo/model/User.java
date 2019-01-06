package com.minarchenko.busdepo.model;

public class User {
    private int id;
    private String user_name;
    private String login;
    private String password;
    private String user_role;

    public User(int id, String user_name, String login, String password, String user_role) {
        this.id = id;
        this.user_name = user_name;
        this.login = login;
        this.password = password;
        this.user_role = user_role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (user_name != null ? !user_name.equals(user.user_name) : user.user_name != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return user_role != null ? user_role.equals(user.user_role) : user.user_role == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user_name != null ? user_name.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (user_role != null ? user_role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", user_role='" + user_role + '\'' +
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

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
