package com.revature.project_0.screens;

/**
 * Abstract Screen class, defines required methods and fields for all screens.
 *
 * @author Wezley Singleton
 * @author Everett Diuguid
 */

public abstract class Screen {

    protected String name;
    protected String route;

    public Screen(String name, String route) {
        this.name = name;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }

    public abstract void render();

}
