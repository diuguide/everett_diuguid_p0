package com.revature.project_0.screens;

import com.revature.project_0.util.ScreenRouter;
import java.io.BufferedReader;
import static com.revature.project_0.Driver.app;

/**
 * Welcome screen.  Landing page for application.  Gives user option to register, login, or exit application
 *
 * @author Everett Diuguid
 */
public class WelcomeScreen extends Screen {

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("WelcomeScreen", "/WelcomeScreen");
        this.consoleReader = consoleReader;
        this.router = router;
    }
    public void render() {
        try {

            String choice;

            // Initial Menu
            System.out.println("Welcome! Please choose from the following menu:");
            System.out.println("+---------------------------------------------+");
            System.out.println("1) Register New Account");
            System.out.println("2) Login");
            System.out.println("3) Exit application");
            System.out.print(">>>> ");
            choice = consoleReader.readLine();

            switch (choice) {
                case "1": {
                    router.navigate("/register");
                    break;
                }
                case "2": {
                    router.navigate("/login");
                    break;
                }
                case "3": {
                    app().setAppRunning(false);
                    break;
                }
                default: {
                    System.out.println("Invalid Entry");
                    break;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}
