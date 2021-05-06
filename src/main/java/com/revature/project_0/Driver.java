package com.revature.project_0;

import com.revature.project_0.util.AppState;

public class Driver {

    private static AppState app = new AppState();

    public static void main(String[] args) {
        while(app.isAppRunning()) {
            app.getRouter().navigate("/WelcomeScreen");
        }
    }

    public static AppState app() {
        return app;
    }
}
