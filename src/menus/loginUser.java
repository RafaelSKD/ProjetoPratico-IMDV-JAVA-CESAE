package menus;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static menus.menuUser.userMenu;
import static utils.console.cleanConsole;
import static utils.console.stop;
import static utils.print.headerAdmin;
import static utils.print.headerUser;

/**
 * This class handles the login and registration process for guest users (clients).
 */
public class loginUser {

    /**
     * Main method used for testing the user registration and login flow.
     *
     * @param args command-line arguments (not used)
     * @throws FileNotFoundException if any required file for the registration process is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        userLogin(); // Launch the user registration flow
    }

    /**
     * Handles the user registration process for a guest client.
     *
     * This method prompts the user to input a username, password, name, contact number, and email.
     * After successful data entry, it displays a confirmation message and redirects the user to the client menu.
     *
     * @throws FileNotFoundException if a required file is not found (currently not used directly in this method)
     */
    public static void userLogin() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        headerUser(); // Display user login header

        // Prompt user for account registration information
        System.out.print("\n                              Efetuar novo registo\n\n" +
                "      Insira um nome de utilizador: ");
        String user = input.nextLine();

        System.out.print("\n               Insira uma password: ");
        String password = input.nextLine();

        System.out.print("\n                       Insira nome: ");
        String name = input.nextLine();

        System.out.print("\n                   Insira contacto: ");
        String contact = input.nextLine();

        System.out.print("\n                      Insira email: ");
        String email = input.nextLine();

        stop(); // Wait for user input to continue
        // Show success message with user info
        successMensageLogin(user, password, name, contact, email);
        userMenu(); // Redirects to user/client menu
    }

    /**
     * Displays a success message after user registration, showing the user's information.
     *
     * @param user     the username of the registered user
     * @param password the password of the registered user
     * @param name     the full name of the registered user
     * @param contact  the contact number of the registered user
     * @param email    the email address of the registered user
     */
    public static void successMensageLogin(String user, String password, String name, String contact, String email){
        headerUser(); // Display user header again for consistency
        System.out.print("\n                         Registo efetuado com sucesso! ✔\n" +
                "\n           Nome de utilizador = " + user +
                "  \n                     Password = " + password + "  \n" +
                "\n                         Nome = " + name +
                "  \n                     Contacto = " + contact + "  \n" +
                "                        Email = " + email + "  \n\n"
        );
        stop(); // Waits for user input before continuing
    }
}
