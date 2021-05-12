package com.revature.project_0.screens;

import com.revature.project_0.services.BankActions;
import com.revature.project_0.services.AccountService;
import com.revature.project_0.models.Transaction;
import com.revature.project_0.util.LinkedList;
import com.revature.project_0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Transaction history screen.  Uses accountService to query all transactions associated with a userId
 *
 * @author Everett Diuguid
 */
public class TransactionHistory extends Screen{

    final private BufferedReader consoleReader;
    final private ScreenRouter router;

    public TransactionHistory(BufferedReader consoleReader, ScreenRouter router) {
        super("TransactionHistory", "/transactionHistory");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    public void render() {

        AccountService accountService = new AccountService();
        BankActions bankActions = new BankActions(router.currentUser);
        LinkedList transactionList = accountService.getTransactions(router.currentUser.getUserId());

        for(int i = 0; i < transactionList.size(); i++) {

            Transaction item = (Transaction) transactionList.get(i);
            System.out.println(item.getTransaction_date() + " " + item.getType() + " " + bankActions.formatBalance(item.getAmount()));
        }

        try {

            String choice;
            System.out.println("+----------------------+");
            System.out.println("1) Return to Account Details");
            System.out.print(">>>>>> ");
            choice = consoleReader.readLine();

            switch (choice) {
                case "1": {
                    router.navigate("/transactions");
                    break;
                }
                default: {
                    router.navigate("/transactionHistory");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
