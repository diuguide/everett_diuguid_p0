package com.revature.project_0.screens;

import com.revature.project_0.database.BankActions;
import com.revature.project_0.util.ScreenRouter;
import java.io.BufferedReader;

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

        System.out.println("Transaction Screen");
        System.out.println("+-----------------+");
        System.out.println("Balance: " + bankActions.formatBalance(bankActions.getBalance()));
        System.out.println("+-----------------+");
        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("3) Back to Account screen");
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
                    router.navigate("/account");
                }

            }

        } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
