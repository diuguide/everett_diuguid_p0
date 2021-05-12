package com.revature.project_0.util;

import com.revature.project_0.dao.UserDAO;
import com.revature.project_0.screens.*;
import com.revature.project_0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Main state of application.  Sets appRunning field on intialization.  Intitializes BufferedReader, userDao, userService for use throughout application
 *
 * @author Everett Diuguid
 * @author Wezley Singleton
 */
public class AppState {

    private ScreenRouter router;
    private boolean appRunning;

    public AppState() {

        // Sets appState
        appRunning = true;

        // Initializes console reader to be used throughout all screens
        final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        final UserDAO userDAO = new UserDAO();
        final UserService userService = new UserService(userDAO);

        // Initialize screen router
        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader, router))
                .addScreen(new LoginScreen(consoleReader, router, userService))
                .addScreen(new RegisterScreen(consoleReader, router, userService))
                .addScreen(new AccountScreen(consoleReader, router))
                .addScreen(new TransactionScreen(consoleReader, router))
                .addScreen(new NewAccountScreen(consoleReader, router))
                .addScreen(new TransactionHistory(consoleReader, router));

        System.out.println("Application started");
    }

    public ScreenRouter getRouter() { return router; }

    public boolean isAppRunning() {
        return appRunning;
    }
    public void setAppRunning(boolean appRunning) { this.appRunning = appRunning;}
}
