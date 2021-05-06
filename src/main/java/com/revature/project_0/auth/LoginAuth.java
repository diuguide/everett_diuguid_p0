package com.revature.project_0.auth;

import com.revature.project_0.models.ActiveUser;

public class LoginAuth {

    private String username;
    private String password;

    public LoginAuth(ActiveUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
