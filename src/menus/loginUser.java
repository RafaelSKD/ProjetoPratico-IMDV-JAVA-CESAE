package menus;

import java.util.Scanner;

import static menus.menuUser.userMenu;
import static utils.consoleCleaner.cleanConsole;

public class loginUser {
    public static void main(String[] args) {
        userLogin();
    }
    public static void userLogin() {
        Scanner input = new Scanner(System.in);
        cleanConsole();
                                                                                      // Login menu for client guest
        System.out.println("================================ IMDV ================================");
        System.out.println("____________________           CLIENTE            ____________________");
        System.out.print("\n                         Efetuar novo registo\n\n" +
                           "                Nome de utilizador: ");
        input.next();
        System.out.print("\n                          Password: ");
        input.next();

        userMenu();                                                                  // run to client/user Menu
    }
}
