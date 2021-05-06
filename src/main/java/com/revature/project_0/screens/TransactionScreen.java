package com.revature.project_0.screens;

import com.revature.project_0.util.ScreenRouter;

import java.io.BufferedReader;

public class TransactionScreen extends Screen{

    private BufferedReader consoleReader;
    private ScreenRouter router;

    public TransactionScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("TransactionScreen", "/transactions");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {
        System.out.println("Transaction Screen");
    }
}
