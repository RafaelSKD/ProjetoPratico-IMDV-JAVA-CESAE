package pages;

import java.io.FileNotFoundException;

import static utils.console.cleanConsole;
import static utils.print.printContent;

/**
 * This class displays the end-of-session screen for the user.
 */
public class endPage {

    public static void main(String[] args) throws FileNotFoundException {
        endSession(); // Launch the end session screen
    }

    /**
     * Displays the final screen when the user logs out or exits the program.
     *
     * This method:
     * - Clears the console
     * - Shows a farewell message and developer photo
     * - Displays copyright
     * - Exits the application
     *
     * @throws FileNotFoundException if any of the display files are missing
     */
    public static void endSession() throws FileNotFoundException {
        cleanConsole(); // Clear the screen for a clean exit
        printContent("IMDV/RafaelCosta.txt", 0); // Print a photo of the developer and greetings
        System.out.println(); // Add spacing
        printContent("IMDV/IMDV_Copyright.txt", 0); // Display copyright
        System.exit(0);// Exit the application
    }
}
