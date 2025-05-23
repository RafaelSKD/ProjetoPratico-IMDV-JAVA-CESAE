package menus;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static main.main.secondMain;
import static utils.console.cleanConsole;
import static utils.print.printFileRatings;

public class menuAdmin {
    /**
     * Main method used to launch the admin menu interface for testing.
     *
     * @param args command-line arguments (not used)
     * @throws FileNotFoundException if any required file for the admin menu operations is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        adminMenu();
    }

    /**
     * Displays the admin menu interface and handles option selection.
     *
     * Provides the admin with various options such as viewing files, checking total ratings,
     * and printing all studios. The input is validated to ensure a valid option is selected.
     * If the admin chooses to log out (option 0), the method redirects to the welcome page.
     *
     * @throws FileNotFoundException if any required file for the menu operations is not found
     */
    public static void adminMenu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int option;
        do {
            cleanConsole();
            System.out.println("================================ IMDV ================================");
            System.out.println("____________________            ADMIN             ____________________");
            System.out.println("\n____________________             MENU             ____________________");
            System.out.println("\n\n                               Escolha:\n");
            System.out.println("                    #1- Consulta de Ficheiros");
            System.out.println("                    #2- Total de Ratings");
            System.out.println("                    #3- Imprimir Todos os Estúdios");
            System.out.println("\n                    #0- Logout");
            System.out.print("\n                    Opcao: ");
            option = input.nextInt();
        } while (option < 0 || option > 3);                                 // Makes sure the user choose a valid option

        if (option == 0)                                                                    // Redirects to welcome page
            secondMain();                                                                         // to make login again

        adminMenuDispacher(option);                                                            // Redirects to dispacher
    }

    /**
     * Routes the admin's menu selection to the corresponding functionality.
     *
     * Based on the selected option, this method performs one of the following:
     * <ul>
     *   <li><b>1</b> - Displays all file ratings</li>
     *   <li><b>2</b> - Shows the total number of ratings</li>
     *   <li><b>3</b> - Prints a list of all studios</li>
     * </ul>
     *
     * @param option the admin menu option selected
     * @throws FileNotFoundException if the ratings file cannot be found
     */
    public static void adminMenuDispacher(int option) throws FileNotFoundException {

        switch (option){
            case 1:                                                                         // Case 1: View file ratings
                printFileRatings("all");                                                 // print all file ratings
                break;
            case 2:                                         // Case 2: Display the total number of ratings in the system
                printFileRatings("number");
                break;
            case 3:                                                               // Case 3: Print a list of all studios
                printFileRatings("studio");
                break;
        }
    }
}
