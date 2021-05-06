package com.revature.project_0.util;

import com.revature.project_0.screens.LoginScreen;
import com.revature.project_0.screens.RegisterScreen;
import com.revature.project_0.screens.WelcomeScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState() {
        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader))
                .addScreen(new RegisterScreen(consoleReader));

        System.out.println("Application started");
    }

    public ScreenRouter getRouter() { return router; }

    public boolean isAppRunning() {
        return appRunning;
    }
    public void setAppRunning(boolean appRunning) { this.appRunning = appRunning;}
}
