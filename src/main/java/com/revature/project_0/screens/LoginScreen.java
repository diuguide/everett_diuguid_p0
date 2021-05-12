package com.revature.project_0.screens;

import com.revature.project_0.models.AppUser;
import com.revature.project_0.services.UserService;
import com.revature.project_0.util.ScreenRouter;
import java.io.BufferedReader;

/**
 * Login screen.  Checks user credentials, if validated redirects to account creation screen
 *
 * @author Everett Diuguid
 */
public class LoginScreen extends Screen {

    private BufferedReader consoleReader;
    private UserService userService;
    private ScreenRouter router;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LoginScreen", "/login");
        this.consoleReader = consoleReader;
        this.router = router;
        this.userService = userService;
    }

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

            if (username != null && password != null) {
                AppUser authenticatedUser = userService.authenticate(username, password);
                if (authenticatedUser != null) {
                    System.out.println("Login Successful!");
                    if (userService.checkForAccount(authenticatedUser.getUserId())) {
                        authenticatedUser.setHasAccount();
                    }
                    router.setCurrentUser(authenticatedUser);
                    router.navigate("/account");
                } else {
                    System.out.println("Login Failed!");
                }
            } else {
                System.out.println("Please complete all fields");
            }





        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
