package main;

import static menus.login.loginMenu;
import static menus.loginMenuDispacher.loginDispacher;
import static pages.welcomePage.welcome;

public class main {
    public static void main(String[] args) {
        welcome();
        loginDispacher(loginMenu());
    }
}
