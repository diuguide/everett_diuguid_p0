package com.revature.project_0.screens;

import com.revature.project_0.models.NewUser;

import java.io.BufferedReader;

public class RegisterScreen extends Screen{

    private BufferedReader consoleReader;

    public RegisterScreen(BufferedReader consoleReader) { this.consoleReader = consoleReader; }

    public void render() {
        try {

            String firstName;
            String lastName;
            String username;
            String password;

            // Register form
            System.out.println("Please fill out form:");
            System.out.println("+-------------------+");
            System.out.print("First Name: ");
            firstName = consoleReader.readLine();
            System.out.print("Last Name: ");
            lastName = consoleReader.readLine();
            System.out.print("Username: ");
            username = consoleReader.readLine();
            System.out.print("Password: ");
            password = consoleReader.readLine();

            NewUser newUser = new NewUser(firstName, lastName, username, password);
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
