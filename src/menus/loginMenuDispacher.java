package menus;

import static menus.loginUser.userLogin;

public class loginMenuDispacher {
    public static void loginDispacher(int option) {
        if (option == 1) {
            userLogin();
        }
    }
}
