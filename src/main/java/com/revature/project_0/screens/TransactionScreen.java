package com.revature.project_0.screens;

import com.revature.project_0.database.BankActions;
import com.revature.project_0.util.ScreenRouter;
import java.io.BufferedReader;

import static com.revature.project_0.Driver.app;

public class TransactionScreen extends Screen{

    final private BufferedReader consoleReader;
    final private ScreenRouter router;

    public TransactionScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("TransactionScreen", "/transactions");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

        BankActions bankActions = new BankActions(router.getCurrentUser());

        String choice = null;
        String depositAmt = null;
        String withdrawAmt = null;

        System.out.println("Account Details:");
        System.out.println("+-----------------+");
        System.out.println("**To Deposit funds select 1**");
        System.out.println("**To Withdraw funds select 2**");
        System.out.println("+-----------------+");
        System.out.println("Balance: " + bankActions.formatBalance(bankActions.getBalance()));
        System.out.println("+-----------------+");
        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("3) Exit application");

        try {
            System.out.print(">>>>> ");
            choice = consoleReader.readLine();

            switch (choice) {
                case "1": {
                    System.out.print("Deposit amount: $");
                    depositAmt = consoleReader.readLine();
                    double dbl = Double.parseDouble(depositAmt);
                    bankActions.deposit(dbl);
                    router.navigate("/transactions");
                    break;
                }
                case "2": {
                    System.out.print("Withdraw amount: $");
                    withdrawAmt = consoleReader.readLine();
                    double dbl = Double.parseDouble(withdrawAmt);
                    bankActions.withdraw(dbl);
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
            System.out.println("Invalid Entry");
            router.navigate("/transactions");
        }
    }
}
