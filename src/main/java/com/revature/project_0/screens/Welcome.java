package com.revature.project_0.screens;

import java.io.BufferedReader;

public class Welcome {

    private BufferedReader consoleReader;

    public Welcome(BufferedReader consoleReader) { this.consoleReader = consoleReader; }
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

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}
