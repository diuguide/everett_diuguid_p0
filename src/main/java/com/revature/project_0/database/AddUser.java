package com.revature.project_0.database;

import com.revature.project_0.models.NewUser;
import com.revature.project_0.screens.LoginScreen;
import com.revature.project_0.screens.RegisterScreen;

import java.io.BufferedReader;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddUser {

     BufferedReader consoleReader;
     String firstName;
     String lastName;
     String username;
     String password;

    public AddUser(NewUser newUser, BufferedReader consoleReader) {
        this.firstName = newUser.getFirstName();
        this.lastName = newUser.getLastName();
        this.username = newUser.getUsername();
        this.password = newUser.getPassword();
        this.consoleReader = consoleReader;

    }
    private static String generateInsert(String firstName, String lastName, String username, String password) {
        return "INSERT INTO users(firstname, lastname, username, password) " + "VALUES ('" + firstName + "','" + lastName + "','" + username + "','" + password + "')";
    }

    public void insertRow() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/project0", "postgres", "beachhouse");
             Statement statement = conn.createStatement()) {

            System.out.println(generateInsert(firstName, lastName, username, password));

            int row = statement.executeUpdate(generateInsert(firstName, lastName, username, password));

            System.out.println(row);

            if (row == 1) {
                System.out.println("User added to database");
                LoginScreen loginScreen = new LoginScreen(consoleReader);
                loginScreen.render();

            } else {
                System.out.println("Add user failed, please try again!");
                RegisterScreen registerScreen = new RegisterScreen(consoleReader);
                registerScreen.render();

            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
