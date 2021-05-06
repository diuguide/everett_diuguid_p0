package com.revature.project_0.models;

public class ActiveUser {

    private String username;
    private String password;

    public ActiveUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
