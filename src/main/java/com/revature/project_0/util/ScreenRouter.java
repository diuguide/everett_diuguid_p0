package com.revature.project_0.util;

import com.revature.project_0.models.AppUser;
import com.revature.project_0.screens.Screen;

public class ScreenRouter {

    // Initializes LinkedList to store active screens
    private LinkedList<Screen> screens = new LinkedList<>();

    // Adds screen to LinkedList
    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    // Traverse through LinkedList to grab appropriate screen
    public void navigate(String route) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if ( screen.getRoute().equals(route)) {
                screen.render();
            }
        }
    }


}
