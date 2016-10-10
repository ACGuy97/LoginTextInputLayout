package com.barranquero.logintextinputlayout.model;

/**
 * Model class
 */
public class User {
    private String user;
    private String password;

    public User(String password, String user) {
        this.password = password;
        this.user = user;
    }

    /**
     * user getter and setter
     */
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * password getter and setter
     */
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}