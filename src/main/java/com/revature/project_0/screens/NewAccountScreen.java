package com.revature.project_0.screens;

import java.io.BufferedReader;

public class NewAccountScreen extends Screen{

    private BufferedReader consoleReader;

    public NewAccountScreen(BufferedReader consoleReader) { this.consoleReader = consoleReader; }

    @Override
    public void render() {
        try {
            String choice;

            //Account Menu
            System.out.println("Select Account Type: ");
            System.out.println("+---------------+");
            System.out.println("1)Checking");
            System.out.println("5)Back");
            System.out.print(">>>>> ");
            choice = consoleReader.readLine();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
