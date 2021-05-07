package com.revature.project_0.screens;

import com.revature.project_0.models.AppUser;
import com.revature.project_0.util.ScreenRouter;

import java.io.BufferedReader;

import static com.revature.project_0.Driver.app;

public class AccountScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private AppUser currentUser;

    public AccountScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("AccountScreen", "/account");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {
        currentUser = router.getCurrentUser();

        try {
            String choice;

            //Account Menu
            System.out.println("Account Options: ");
            System.out.println("+---------------+");
            System.out.println("1)Open New Account");
            System.out.println("2)View Balance, Deposit, Withdraw");
            System.out.println("3)Exit Application");
            System.out.print(">>>>> ");
            choice = consoleReader.readLine();

            switch (choice) {
                case "1": {
                    router.navigate("/newAccount");
                    break;
                }
                case "2": {
                    router.navigate("/transactions");
                    break;
                }
                case "3": {
                    System.out.println("You have been Logged Out!");
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
