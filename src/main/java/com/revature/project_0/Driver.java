package com.revature.project_0;

import com.revature.project_0.screens.Welcome;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {

    public static void main(String[] args) {

            try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                Welcome welcome = new Welcome(consoleReader);
                welcome.render();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
