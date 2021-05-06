package com.revature.project_0.screens;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        super("LoginScreen", "login");
        this.consoleReader = consoleReader; }

    public void render() {
        try {

            String username;
            String password;

            //Login form
            System.out.println("Log into your account!");
            System.out.println("+---------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();





        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
