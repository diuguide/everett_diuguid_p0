package com.revature.project_0.screens;

import com.revature.project_0.Exceptions.InvalidRequestException;
import com.revature.project_0.Exceptions.UsernameNotAvailable;
import com.revature.project_0.models.AppUser;
import com.revature.project_0.services.UserService;
import com.revature.project_0.util.ScreenRouter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Registration Screen.  Gathers newUser data and submits that to userService
 *
 * @author Everett Diuguid
 */
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
            AppUser newUser = new AppUser(firstName, lastName, username, password);
            userService.register(newUser);
            System.out.println("New Account created!");
            System.out.println("+------------------+");
            router.navigate("/login");


        } catch (UsernameNotAvailable e) {
            System.out.println("Username Not Available!");
            router.navigate("/register");
        } catch (InvalidRequestException e) {
            System.out.println("Please enter valid data!");
            router.navigate("/register");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
