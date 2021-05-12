package com.revature.project_0.screens;

import com.revature.project_0.Exceptions.InvalidInputException;
import com.revature.project_0.services.BankActions;
import com.revature.project_0.services.AccountService;
import com.revature.project_0.util.ScreenRouter;
import java.io.BufferedReader;
import static com.revature.project_0.Driver.app;

/**
 * Account Details screen.  Gives user option to deposit, withdraw, view transactions.  Displays current balance.
 *
 * @author Everett Diuguid
 */
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
        AccountService accountService = new AccountService();

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
        System.out.println("3) View Transaction History");
        System.out.println("4) Exit application");

        try {
            System.out.print(">>>>> ");
            choice = consoleReader.readLine();

            switch (choice) {
                case "1": {
                    System.out.print("Deposit amount: $");
                    try {
                        depositAmt = consoleReader.readLine();
                        double dbl = Double.parseDouble(depositAmt);
                        boolean inputCheck = accountService.checkInput(dbl);
                        if (inputCheck) {
                            bankActions.deposit(dbl);
                        }
                        router.navigate("/transactions");
                    } catch (InvalidInputException e) {
                        System.err.println("Deposit amount must be greater than zero");
                        router.navigate("/transactions");
                    } catch (NumberFormatException e) {
                        System.err.println("Please Enter a number greater than zero");
                        router.navigate("/transactions");
                    }
                    break;
                }
                case "2": {
                    System.out.print("Withdraw amount: $");
                    try {
                        withdrawAmt = consoleReader.readLine();
                        double dbl = Double.parseDouble(withdrawAmt);
                        boolean inputCheck = accountService.checkInput(dbl);
                        if (inputCheck) {
                            bankActions.withdraw(dbl);
                        }
                        router.navigate("/transactions");
                    } catch (InvalidInputException e) {
                        System.err.println("Withdraw amount must be greater than zero");
                        router.navigate("/transactions");
                    } catch (NumberFormatException e) {
                        System.err.println("Please Enter a number greater than zero");
                        router.navigate("/transactions");
                    }
                    break;
                }
                case "3": {
                    router.navigate("/transactionHistory");
                }
                case "4": {
                    app().setAppRunning(false);
                    break;
                }
                default: {
                    System.out.println("Invalid Entry");
                    router.navigate("/transactions");
                    break;
                }

            }

        } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
