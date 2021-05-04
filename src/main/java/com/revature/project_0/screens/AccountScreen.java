package com.revature.project_0.screens;

import java.io.BufferedReader;

public class AccountScreen extends Screen{

    private BufferedReader consoleReader;

    public AccountScreen(BufferedReader consoleReader) { this.consoleReader = consoleReader; }

    @Override
    public void render() {
        try {
            String choice;

            //Account Menu
            System.out.println("Account Options: ");
            System.out.println("+---------------+");
            System.out.println("1)Open New Account");
            System.out.println("2)View Balance");
            System.out.println("3)Deposit");
            System.out.println("4)Withdraw");
            System.out.println("5)Logout");
            System.out.print(">>>>> ");
            choice = consoleReader.readLine();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
