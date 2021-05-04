package com.revature.project_0.screens;

import java.io.BufferedReader;

public class WelcomeScreen extends Screen {

    private BufferedReader consoleReader;

    public WelcomeScreen(BufferedReader consoleReader) { this.consoleReader = consoleReader; }
    public void render() {
        try {

            String choice;

            // Initial Menu
            System.out.println("Welcome! Please choose from the following menu:");
            System.out.println("+---------------------------------------------+");
            System.out.println("1) Register New Account");
            System.out.println("2) Login");
            System.out.print(">>>> ");
            choice = consoleReader.readLine();

            switch (choice) {
                case "1": {
                    RegisterScreen registerScreen = new RegisterScreen(consoleReader);
                    registerScreen.render();
                    break;
                }
                case "2": {
                    LoginScreen loginScreen = new LoginScreen(consoleReader);
                    loginScreen.render();
                    break;
                }
                default: {
                    break;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}
