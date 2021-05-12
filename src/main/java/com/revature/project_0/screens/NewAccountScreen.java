package com.revature.project_0.screens;

import com.revature.project_0.services.BankActions;
import com.revature.project_0.util.ScreenRouter;
import java.io.BufferedReader;

/**
 * New Account Screen.  Allows user to create new account
 *
 * @author Everett Diuguid
 */
public class NewAccountScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private BankActions bankActions;

    public NewAccountScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("NewAccountScreen", "/newAccount");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

        bankActions = new BankActions(router.getCurrentUser());

        try {
            String choice;

            //Account Menu
            System.out.println("Select Account Type: ");
            System.out.println("+---------------+");
            System.out.println("1)Checking");
            System.out.println("2)Back");
            System.out.print(">>>>> ");
            choice = consoleReader.readLine();

            switch (choice) {
                case "1": {
                    bankActions.createAccount();
                    router.currentUser.setHasAccount();
                    System.out.println("Created checking account!");
                    router.navigate("/account");
                }
                case "2": {
                    router.navigate("/account");
                    break;
                }
                default: {
                    System.out.println("Invalid Response");
                    router.navigate("/newAccount");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
