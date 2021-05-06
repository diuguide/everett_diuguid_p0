package com.revature.project_0.screens;

import java.io.BufferedReader;

public class TransactionScreen extends Screen{

    private BufferedReader consoleReader;

    public TransactionScreen(BufferedReader consoleReader) {
        super("TransactionScreen", "transaction");
        this.consoleReader = consoleReader;
    }

    @Override
    public void render() {
        System.out.println("Transaction Screen");
    }
}
