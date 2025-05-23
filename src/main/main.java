package main;

import java.io.FileNotFoundException;

import static menus.login.loginMenu;
import static menus.loginMenuDispacher.loginDispacher;
import static pages.welcomePage.welcome;

public class main {
    /**
     * Main method that starts the application by displaying the welcome screen and
     * directing the user to the appropriate login flow (admin or client).
     *
     * @param args command-line arguments (not used)
     * @throws FileNotFoundException if any required file for login is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        welcome();
        loginDispacher(loginMenu());
    }

    /**
     * Duplicate entry point for restarting the application flow without requiring command-line arguments.
     *
     * Displays the welcome screen and redirects the user to the appropriate login flow.
     * Useful for internal redirection (e.g., after logout) within the application.
     *
     * @throws FileNotFoundException if any required file for login is not found
     */

    public static void secondMain() throws FileNotFoundException {
        welcome();
        loginDispacher(loginMenu());
    }
}
