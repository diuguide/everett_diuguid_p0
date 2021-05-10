package com.revature.project_0.screens;

import com.revature.project_0.models.AppUser;
import com.revature.project_0.services.UserService;
import com.revature.project_0.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private UserService userService;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("RegisterScreen", "/register");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userService = userService;
    }

    @Override
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

            // Create new user object
            if (firstName != null && lastName != null && username != null && password != null) {
                AppUser newUser = new AppUser(firstName, lastName, username, password);
                userService.register(newUser);
                router.navigate("/login");
            } else {
                System.out.println("Please enter all fields");
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
