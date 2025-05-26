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
 * Options supported:
 * <ul>
 *   <li><b>"all"</b> - Prints all ratings listed in the file</li>
 *   <li><b>"number"</b> - Prints the total number of ratings (excluding header)</li>
 *   <li><b>"studio"</b> - Prints all the studios without duplicates</li>
 * </ul>
 *
 * After each action, the method pauses and then returns to the admin menu.
 */
public class print {

    /**
     * Main dispatcher method to display rating content based on user selection.
     *
     * @param option the display option selected ("all", "number", or "studio")
     * @throws FileNotFoundException if the ratings file cannot be found
     */
    public static void printFileRatings(String option) throws FileNotFoundException {
        String src = "IMDV/IMDV.csv"; // File path
        String splitter = ";"; // CSV delimiter

        headerAdmin();
        System.out.println();

        if (option.equals("all")){
            printContent(src, 1);         // Print all ratings, skipping header
            stop();                       // Wait for user input
            adminMenu();                 // Return to admin menu
        }
        if (option.equals("number")){
            printContentNumberOfLines(src); // Show number of ratings
            stop();
            adminMenu();
        }
        if (option.equals("studio")){
            printContentListOf(src, splitter, 5); // Print studios (column 5) without duplicates
            stop();
            adminMenu();
        }
    }

    /**
     * Counts and prints the total number of data lines (excluding the header) in a given file.
     *
     * @param src the path to the source file
     * @throws FileNotFoundException if the specified file cannot be found
     */
    public static void printContentNumberOfLines(String src) throws FileNotFoundException {
        Scanner data = new Scanner(new File(src));
        int counter = 0;

        while (data.hasNextLine()) {
            data.nextLine();
            counter++;
        }

        counter--; // subtract 1 to exclude header
        headerAdmin();
        System.out.println("\n\n                   O total de ratings atribuídos é de: " + counter);
        data.close();
    }

    /**
     * Prints all lines of a file to the console, excluding the header line if flag is set.
     *
     * @param src  the path to the source file
     * @param flag if 1, skips the header line; if 0, includes all lines
     * @throws FileNotFoundException if the specified file cannot be found
     */
    public static void printContent(String src, int flag) throws FileNotFoundException {
        Scanner data = new Scanner(new File(src));
        String linha;
        if (flag == 1)
            data.nextLine(); // Skip header if flag is 1

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
     * @param src             the path to the source file
     * @param splitter        the delimiter used to split values in each line
     * @param targetposition  variable arguments:
     *                        - targetposition[0]: column used as unique key
     *                        - targetposition[1...n]: additional columns to print next to key
     * @throws FileNotFoundException if the specified file cannot be found
     */
    public static void printContentListOf(String src, String splitter, int... targetposition) throws FileNotFoundException {
        String[][] file = fileToMatrix(src, splitter, 7); // Read file into matrix with 7 columns
        int numberMaxOfItems = countLinesInFile(src); // Used to size the 'printed' array
        String[] printed = new String[numberMaxOfItems]; // Track printed keys to avoid duplicates
        int y = 0;

        header(); // Show header UI

        for (int i = 1; i < file.length; i++) { // Skip header row
            if (targetposition[0] >= file[i].length || file[i][targetposition[0]].isEmpty())
                continue;
            String key = file[i][targetposition[0]];

            if (!isInArray(printed, key)) { // Check for duplicates
                printed[y] = key;
                y++;
                System.out.println(" - " + key);

                for (int j = 1; j < targetposition.length; j++) {
                    int pos = targetposition[j];
                    if (pos < file[i].length) {
                        System.out.println
                                (" - " + file[i][pos]);
                    }
                }
                System.out.println();
            }
        }
    }

    // UI header methods
    public static void headerUser(){
        cleanConsole();
        System.out.println("╔════════════════════════════════      IMDV      ═══════════════════════════════╗");
        System.out.println("║═══════════════════             👤  CLIENTE  👤             ═══════════════════║");
    }

    public static void headerAdmin(){
        cleanConsole();
        System.out.println("╔════════════════════════════════      IMDV      ═══════════════════════════════╗");
        System.out.println("║═══════════════════              🛠️  ADMIN  🛠️              ═══════════════════║");
    }

    public static void header(){
        cleanConsole();
        System.out.println("╔════════════════════════════════      IMDV      ═══════════════════════════════╗");
        System.out.println("║                                                                               ║\n\n");
    }

    public static void headerLogin(){
        cleanConsole();
        System.out.println("╔════════════════════════════════      IMDV      ═══════════════════════════════╗");
        System.out.println("║═════════════════════            🔐  LOGIN  🔐            ═════════════════════║");
    }

    public static void headerMenu(){
        System.out.println("║                                                                               ║");
        System.out.println("║═════════════════════             ▒▒  MENU  ▒▒            ═════════════════════║");
    }

    public static void headerQuiz(){
        cleanConsole();
        System.out.println("╔════════════════════════════════      IMDV      ═══════════════════════════════╗");
        System.out.println("║═════════════════════        💡  QUIZ DE CINEMA  💡       ═════════════════════║");
    }
}
