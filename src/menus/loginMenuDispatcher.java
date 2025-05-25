package menus;

import java.io.FileNotFoundException;

import static menus.loginAdmin.adminLogin;
import static menus.loginUser.userLogin;
import static pages.endPage.endSession;
import static utils.console.cleanConsole;

/**
 * This class is responsible for dispatching the user to the correct login flow
 * depending on the selected option from the main login menu.
 */
public class loginMenuDispatcher {

    /**
     * Dispatches the login flow based on the user's selected option.
     *
     * @param option the login type selected (1 for admin, 2 for user, 0 to exit)
     * @throws FileNotFoundException if a required file for login is not found
     */
    public static void loginDispatcher(int option) throws FileNotFoundException {
        if (option == 1)
            adminLogin();      // Launches admin login flow
        if (option == 2)
            userLogin();       // Launches client/user login flow
        if (option == 0)
            endSession();      // Ends the session (exit option)
    }
}
