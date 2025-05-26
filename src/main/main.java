package main;

import java.io.FileNotFoundException;

import static menus.login.loginMenu;
import static menus.loginMenuDispatcher.loginDispatcher;
import static pages.welcomePage.welcome;

/**
 * Main class responsible for launching the application.
 */
public class main {

    /**
     * Initial entry point of the program.
     *
     * @param args command-line arguments (not used in this application)
     * @throws FileNotFoundException if any required file for login is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        secondMain(); // Redirect to secondMain for flexibility in relaunching the app
    }

    /**
     * Secondary entry point used to restart the application flow internally.
     *
     * Displays the welcome screen and then dispatches to the login menu logic.
     * Useful for redirecting the user after logout or session reset.
     *
     * @throws FileNotFoundException if any required file for login is not found
     */
    public static void secondMain() throws FileNotFoundException {
        welcome(); // Show welcome banner
        loginDispatcher(loginMenu()); // Launch login menu and handle routing
    }
}
