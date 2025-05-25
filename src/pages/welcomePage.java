package pages;

import java.util.Scanner;

import static utils.console.cleanConsole;
import static utils.console.stop;

/**
 * This class displays the welcome screen of the application.
 * It provides an ASCII-art based visual greeting before the user navigates further.
 */
public class welcomePage {

    /**
     * Main method used to launch the application by displaying the welcome screen for testing.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        welcome(); // Call welcome screen
    }

    /**
     * Displays the welcome screen with an ASCII art banner and waits for user input to continue.
     *
     * This method serves as an entry point to greet the user before navigating to the main menu.
     */
    public static void welcome() {
        cleanConsole(); // Clears the console before showing the banner
        System.out.print(  // Displays the ASCII art welcome banner
                " _____                                                                       _____ \n" +
                        "( ___ )                                                                     ( ___ )\n" +
                        " |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   | \n" +
                        " |   |                                                                       |   | \n" +
                        " |   |     ____                        __      __ _             _            |   | \n" +
                        " |   |    |  _ \\                       \\ \\    / /(_)           | |           |   | \n" +
                        " |   |    | |_) |  ___  _ __ ___  ______\\ \\  / /  _  _ __    __| |  ___      |   | \n" +
                        " |   |    |  _ <  / _ \\| '_ ` _ \\|______|\\ \\/ /  | || '_ \\  / _` | / _ \\     |   | \n" +
                        " |   |    | |_) ||  __/| | | | | |        \\  /   | || | | || (_| || (_) |    |   | \n" +
                        " |   |    |____/  \\___||_| |_| |_|         \\/    |_||_| |_| \\__,_| \\___/     |   | \n" +
                        " |   |                __ _   _____  __  __  _____ __      __                 |   | \n" +
                        " |   |               / _` | |_   _||  \\/  ||  __ \\\\ \\    / /                 |   | \n" +
                        " |   |              | (_| |   | |  | \\  / || |  | |\\ \\  / /                  |   | \n" +
                        " |   |               \\__,_|   | |  | |\\/| || |  | | \\ \\/ /                   |   | \n" +
                        " |   |                       _| |_ | |  | || |__| |  \\  /                    |   | \n" +
                        " |   |                      |_____||_|  |_||_____/    \\/                     |   | \n" +
                        " |   |                                                                       |   | \n" +
                        " |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___| \n" +
                        "(_____)                                                                     (_____)\n"
        );
        stop(); // Waits for user to press enter before continuing (pause for transition)
    }
}
