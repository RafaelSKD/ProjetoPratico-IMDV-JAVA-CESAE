package utils;

import java.util.Scanner;

public class console {
    /**
     * Simulates clearing the console by printing multiple newline characters.
     *
     */
    public static void cleanConsole(){                                                          // Tool to clean console
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    /**
     * Pauses the program execution until the user provides any input.
     *
     */
    public static void stop(){
        Scanner input = new Scanner(System.in);
        System.out.print("\n\n\nPrecionar qualquer tecla para continuar...  ");       // Wait for user input to continue
        input.next();
    }
}