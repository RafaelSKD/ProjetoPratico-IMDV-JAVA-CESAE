package menus;

import java.io.FileNotFoundException;

import static menus.loginAdmin.adminLogin;
import static menus.loginUser.userLogin;

public class loginMenuDispacher {
    /**
     * Dispatches the login flow based on the user's selected option.
     *
     * @param option the login type selected (1 for admin, 2 for user)
     * @throws FileNotFoundException if a required file for login is not found
     */
    public static void loginDispacher(int option) throws FileNotFoundException {
        if (option == 1)
            adminLogin();
        if (option == 2)
            userLogin();
    }
}
