package menus;

import java.util.Scanner;

import static utils.console.cleanConsole;
import static utils.print.headerLogin;

/**
 * This class handles the login menu functionality for different types of users.
 */
public class login { // Class name should follow PascalCase naming convention (e.g., Login)

    /**
     * Main method used to directly launch the login menu for testing.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        loginMenu(); // Launch the login menu interface
    }

    /**
     * Displays the login menu and prompts the user to choose a login type.
     *
     * @return the user's selected option (1 = Admin, 2 = Client, 0 = Exit)
     */
    public static int loginMenu() {
        Scanner input = new Scanner(System.in);
        int option; // Variable to store user's selected login option

        do {
            cleanConsole(); // Clears the console screen for a clean UI
            headerLogin(); // Prints a formatted header for the login screen

            // Display login options to the user
            System.out.println("\n\n                         Iniciar conta com login de:\n\n" +
                    "                          1# ADMINISTRADOR\n" +
                    "                          2# CLIENTE" +
                    "\n\n                          0# ENCERRAR");

            System.out.print("\n\n                         Escolha uma opcao: ");
            option = input.nextInt(); // Read user's choice
        } while(option < 0 || option > 2); // Ensures the input is within the valid range (0–2)

        return option; // Return the selected option for further processing
    }
}
