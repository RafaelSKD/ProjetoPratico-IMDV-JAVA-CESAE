package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static menus.menuAdmin.adminMenu;
import static utils.arrays.isInArray;
import static utils.console.cleanConsole;
import static utils.console.stop;
import static utils.files.countLinesInFile;
import static utils.files.fileToMatrix;

/**
 * Handles the display of rating data from the IMDV database file based on the selected option.
 *
 *
 * Options supported:
 * <ul>
 *   <li><b>"all"</b> - Prints all ratings listed in the file</li>
 *   <li><b>"number"</b> - Prints the total number of ratings (excluding header)</li>
 *   <li><b>"studio"</b> - Prints all the studios without duplicates</li>
 * </ul>
 *
 * After each action, the method pauses and then returns to the admin menu.
 *
 * @param option the display option selected ("all", "number", or "studio")
 * @throws FileNotFoundException if the ratings file cannot be found
 */
public class print {
    public static void printFileRatings(String option) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String src = "IMDV/IMDV.csv";
        String splitter = ";";

//        String[][] imdv = fileToMatriz("IMDV/IMDV.csv", ";", 8);

        if (option.equals("all")){
            printContent(src);                                                                      // print all ratings
            stop();                                                                  // waits for user input to continue
            adminMenu();                                                                        // returns to admin menu
        }
        if (option.equals("number")){
            printContentNumberOfLines(src);                                             // print total number of ratings
            stop();                                                                  // waits for user input to continue
            adminMenu();                                                                        // returns to admin menu
        }
        if (option.equals("studio")){
            printContentListOf(src, splitter, 5);                                             // print total number of ratings
            stop();                                                                  // waits for user input to continue
            adminMenu();                                                                        // returns to admin menu
        }
    }

    /**
     * Counts and prints the total number of data lines (excluding the header) in a given file.
     *
     * @param src the path to the source file
     * @throws FileNotFoundException if the specified file cannot be found
     */
    private static void printContentNumberOfLines(String src) throws FileNotFoundException {
        Scanner data = new Scanner(new File(src));

        String linha = data.nextLine();  // ignores first header
        int counter = 0;

        while (data.hasNextLine()){
            linha = data.nextLine();
            counter++;
        }
        cleanConsole();
        System.out.println("================================ IMDV ================================");
        System.out.println("____________________            ADMIN             ____________________");
        System.out.println("\n\n                 O total de ratings atribuidos e de: " + counter);
        data.close();
    }
    /**
     * Prints all lines of a file to the console, excluding the header line.
     *
     * @param src the path to the source file
     * @throws FileNotFoundException if the specified file cannot be found
     */
    public static void printContent(String src) throws FileNotFoundException {
        Scanner data = new Scanner(new File(src));

        String linha = data.nextLine();                                                                // ignores header

        while (data.hasNextLine()){
            linha = data.nextLine();
            System.out.println(linha);
        }
        data.close();
    }

    /**
     * Prints a list of unique values from the first specified column and their associated values
     * from additional specified columns, all on the same line.
     *
     * Only the first occurrence of each unique key value is printed, along with corresponding
     * values from the same row in the other given positions.
     *
     * @param src             the path to the source file
     * @param splitter        the delimiter used to split values in each line
     * @param targetposition  an array where the first value is the key column, and the rest are associated columns to print
     * @throws FileNotFoundException if the specified file cannot be found
     */
    public static void printContentListOf(String src, String splitter, int... targetposition) throws FileNotFoundException {
        String[][] file = fileToMatrix(src, splitter, 7);
        int numberMaxOfItems = countLinesInFile(src);
        String[] printed = new String[numberMaxOfItems];
        int y = 0;

        cleanConsole();
        System.out.println("================================ IMDV ================================\n");
        for (int i = 1; i < file.length; i++) {
            if (targetposition[0] >= file[i].length || file[i][targetposition[0]].isEmpty()) continue;
            String key = file[i][targetposition[0]];
            if (!isInArray(printed, key)) {
                printed[y] = key;
                y++;
                System.out.print(" - " + key);
                for (int j = 1; j < targetposition.length; j++) {
                    int pos = targetposition[j];
                    if (pos < file[i].length) {
                        System.out.print(" | " + file[i][pos]);
                    }
                }
                System.out.println();
            }
        }
    }
}
