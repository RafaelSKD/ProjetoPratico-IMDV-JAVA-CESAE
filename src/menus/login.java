package menus;

import java.util.Scanner;

import static utils.console.cleanConsole;

public class login {
    /**
     * Main method used to directly launch the login menu for testing.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        loginMenu();
    }

    public static int loginMenu() {
        Scanner input = new Scanner(System.in);
        int option;                                                               // Login menu to choose type of user
        do {
            cleanConsole();
            System.out.println("================================ IMDV ================================");
            System.out.println("____________________          login menu          ____________________");
            System.out.println("\n\n                    Iniciar conta com login de:\n\n" +
                    "                    1# ADMINISTRADOR\n" +
                    "                    2# CLIENTE" +
                    "\n\n                    0# ENCERRAR SESSAO");
            System.out.print("\n\n                   Escolha uma opcao: ");
            option = input.nextInt();
        }while(option < 0 || option > 2);                                         // handler for wrong answers

        return option;
    }
}
