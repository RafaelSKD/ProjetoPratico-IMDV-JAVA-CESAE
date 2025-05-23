package pages;

import java.util.Scanner;

import static utils.consoleCleaner.cleanConsole;

public class welcomePage {
    public static void main(String[] args) {
        welcome();
    }
    public static void welcome() {
        Scanner input = new Scanner(System.in);
        cleanConsole();
        System.out.print(  // welcome page
                " _____                                                                       _____ \n" +
                        "( ___ )                                                                     ( ___ )\n" +
                        " |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   | \n" +
                        " |   |                                                                       |   | \n" +
                        " |   |     ____                        __      __ _             _            |   | \n" +
                        " |   |    |  _ \\                       \\ \\    / /(_)           | |           |   | \n" +
                        " |   |    | |_) |  ___  _ __ ___  ______\\ \\  / /  _  _ __    __| |  ___      |   | \n" +
                        " |   |    |  _ <  / _ \\| '_ ` _ \\|______|\\ \\/ /  | || '_ \\  / _` | / _ \\     |   | \n" +
                        " |   |    | |_) ||  __/| | | | | |        \\  /   | || | | || (_| || (_) |    |   | \n" +
                        " |   |    |____/  \\___||_| |_| |_|         \\/    |_||_| |_| \\__,_| \\___/     |   | \n" +
                        " |   |                __ _   _____  __  __  _____ __      __                 |   | \n" +
                        " |   |               / _` | |_   _||  \\/  ||  __ \\\\ \\    / /                 |   | \n" +
                        " |   |              | (_| |   | |  | \\  / || |  | |\\ \\  / /                  |   | \n" +
                        " |   |               \\__,_|   | |  | |\\/| || |  | | \\ \\/ /                   |   | \n" +
                        " |   |                       _| |_ | |  | || |__| |  \\  /                    |   | \n" +
                        " |   |                      |_____||_|  |_||_____/    \\/                     |   | \n" +
                        " |   |                                                                       |   | \n" +
                        " |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___| \n" +
                        "(_____)                                                                     (_____)\n"
        );
        System.out.print("\n\nPrecionar qualquer tecla para continuar...  ");  // Wait for user input to continue.
        input.next();
    }
}
