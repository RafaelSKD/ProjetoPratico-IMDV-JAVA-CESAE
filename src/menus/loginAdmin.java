package menus;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static menus.menuAdmin.adminMenu;
import static utils.console.cleanConsole;
import static utils.console.stop;
import static utils.files.fileToMatrix;
import static utils.print.headerAdmin;

/**
 * This class manages the login functionality for admin users.
 */
public class loginAdmin { // Class name should follow PascalCase convention. OK here.

    /**
     * Main method used for testing the admin login functionality.
     *
     * @param args command-line arguments (not used)
     * @throws FileNotFoundException if the admin login file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        adminLogin(); // Start admin login process
    }

    /**
     * Handles the login process for an admin user by validating credentials from a CSV file.
     *
     * The method prompts for username and password in a loop until valid credentials are entered.
     * If the login fails, a warning message is shown before prompting again.
     * Upon successful login, a confirmation message is displayed and the admin menu is launched.
     *
     * @throws FileNotFoundException if the admin credentials file cannot be found
     */
    public static void adminLogin() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String loginAdmin = "IMDV/IMDV_AdminLogin.csv"; // Path to CSV file containing login credentials
        String splitter = ";"; // Delimiter used in the CSV
        String[][] loginMatrix = fileToMatrix(loginAdmin, splitter, 2); // Convert CSV to 2D array (username-password pairs)
        String username = "";
        String password = "";
        int flag = 0; // Used to control error message display

        do {
            if (flag == 1){ // Only show error message after a failed login attempt
                headerAdmin();
                System.out.println("\n\n                                      LOGIN\n");
                System.out.println("\n                      ⚠️ ERROR!: PASSWORD/LOGIN - FAILED ");
                stop(); // Waits for user to press enter before retry
            }

            // Display login prompt
            headerAdmin();
            System.out.print("\n                           Utilizador: ");
            username = input.nextLine(); // Read username input
            System.out.print("\n                             Password: ");
            password = input.nextLine(); // Read password input
            flag = 1; // Set flag to show error message next time if login fails
        } while(!validUserPassword(loginMatrix, username, password)); // Repeat until valid credentials are entered
        // Login success message
        headerAdmin();
        System.out.println("\n\n                                      LOGIN\n");
        System.out.println("\n                         ✅ Login Efetuado com sucesso ");
        stop(); // Wait for user before proceeding
        adminMenu(); // Redirect to admin menu
    }

    /**
     * Verifies if the provided username and password match any entry in the login matrix.
     *
     * This method scans each row of the matrix and checks if the username and corresponding
     * password exist in the same line (adjacent columns).
     *
     * @param loginMatrix the 2D array containing username and password pairs
     * @param username    the username to verify
     * @param password    the password to verify
     * @return true if a matching username and password pair is found in the same row; false otherwise
     */
    public static boolean validUserPassword(String[][] loginMatrix , String username, String password){
        int x = 0, y;
        while (x < loginMatrix.length){
            y = 0;
            while (y < loginMatrix[x].length){
                // Check if username and its corresponding password match
                if (loginMatrix[x][y].equals(username) && loginMatrix[x][y+1].equals(password))
                    return true;
                y++;
            }
            x++;
        }
        return false; // No matching credentials found
    }
}
