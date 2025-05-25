package menus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static main.main.secondMain;
import static pages.quiz.quiz;
import static utils.arrays.*;
import static utils.console.cleanConsole;
import static utils.console.stop;
import static utils.files.countLinesInFile;
import static utils.files.fileToMatrix;
import static utils.print.*;

/**
 * This class provides the client (user) menu interface and its functionalities.
 */
public class menuUser { // Class name should follow PascalCase: "MenuUser"

    /**
     * Main method used to launch the client (user) menu interface for testing.
     */
    public static void main(String[] args) throws FileNotFoundException {
        userMenu();
    }

    /**
     * Displays the user menu and handles input selection.
     *
     * Options include printing catalogs, studios with best/worst ratings,
     * most recent review, filtered catalogs, and accessing a quiz.
     */
    public static void userMenu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int option;

        do {
            headerUser();
            headerMenu();
            System.out.println("\n\n                          Escolha a forma da procura:\n");
            System.out.println("                    #1- Imprimir Catálogo");
            System.out.println("                    #2- Imprimir Catálogos Gráficos");
            System.out.println("                    #3- Imprimir Melhor Estúdio");
            System.out.println("                    #4- Imprimir Pior Estúdio");
            System.out.println("                    #5- Imprimir Crítica Mais Recente");
            System.out.println("                    #6- Quiz");
            System.out.println("                    #7- Imprimir Catálogo Estúdio");
            System.out.println("                    #8- Imprimir Catálogo Categoria");
            System.out.println("\n                    #0- Logout");
            System.out.print("\n                    Opcao: ");
            option = input.nextInt();
        } while (option < 0 || option > 8); // Input validation
        if (option == 0) // Logout to main screen
            secondMain();
        userMenuDispacher(option); // Dispatch selected option
    }

    /**
     * Routes user menu selections to the appropriate actions or submenus.
     *
     * @param option the selected menu option (1 to 8)
     * @throws FileNotFoundException if the data file cannot be found
     */
    public static void userMenuDispacher(int option) throws FileNotFoundException {
        String src = "IMDV/IMDV.csv";
        String splitter = ";";

        switch (option){
            case 1:
                printContentListOf(src, splitter, 1, 2);  // print all movies with ratings
                stop();
                break;
            case 2:
                graphicMenu(); // Navigates to graphic submenu
                break;
            case 3:
                printExtremesInFile(src, splitter, "max");  // print best studio by ratings
                stop();
                break;
            case 4:
                printExtremesInFile(src, splitter, "min");  // print worst studio by ratings
                stop();
                break;
            case 5:
                printMostRecentRating(src); // prints the last entry
                stop();
                break;
            case 6:
                quiz(); // Launch quiz feature
                break;
            case 7:
                printFilter(src, "studio"); // Prints all movies filter by studio
                break;
            case 8:
                printFilter(src, "category"); // Prints all movies filter by category
                break;
        }

        userMenu(); // Re-display the menu after operation
    }

    /**
     * Filters and prints movies from the data file based on studio or category (genre).
     *
     * @param src    the path to the CSV file
     * @param filter the filter type: "studio" or "category"
     * @throws FileNotFoundException if the file cannot be found
     */
    public static void printFilter(String src, String filter) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        String[][] data = fileToMatrix(src, ";", 8);
        headerUser();
        String option;
        int[] index;

        if (filter.equals("studio")) {
            System.out.println("\n\n                    Qual o Estudio que deseja ver o catalogo?");
            System.out.print("\n                             Estudio: ");
            option = input.nextLine();
            index = findPositionOccurrencesInMatrix(data, option, 5); // Studio column = 5
            printContentFilteredByIndex(data, index, 1, 2, 3, 4, 6, 7);
            stop();
        }

        if (filter.equals("category")) {
            System.out.println("\n\n                    Qual o genero que deseja ver o catalogo?");
            System.out.print("\n                             Genero: ");
            option = input.nextLine();
            index = findPositionOccurrencesInMatrix(data, option, 7); // Category column = 7
            printContentFilteredByIndex(data, index, 1, 2, 3, 4, 6, 5);
            stop();
        }
    }

    /**
     * Prints specific fields of selected rows from a data matrix, using column headers.
     *
     * @param data         the full data matrix, including headers in row 0
     * @param index        array of row indices to print (e.g., [2, 4, 5])
     * @param contentIndex array of column indices to display for each selected row
     */
    private static void printContentFilteredByIndex(String[][] data, int[] index, int... contentIndex) {
        for (int i = 0; i < index.length; i++) {
            int rowIndex = index[i]; // Get the row index to print
            for (int j = 0; j < contentIndex.length; j++) {
                int colIndex = contentIndex[j]; // Get the column index to print
                System.out.print("\n   - " + data[0][colIndex] + " : " + data[rowIndex][colIndex]); // Print header and value
            }
            System.out.println("\n\n"); // Add spacing between rows
        }
    }

    /**
     * Reads the last line from the given CSV file and prints it as the most recent rating entry.
     *
     * @param src the path to the CSV file
     * @throws FileNotFoundException if the file cannot be found
     */
    private static void printMostRecentRating(String src) throws FileNotFoundException {
        File file = new File(src);
        Scanner scanner = new Scanner(file);
        String lastLine = "";

        while (scanner.hasNextLine()) {
            lastLine = scanner.nextLine(); // Store last line
        }
        scanner.close();
        headerUser();
        System.out.println("\n\nCrítica mais recente:\n" + lastLine);
    }

    /**
     * Calculates the average ratings for each unique studio in the file and prints either
     * the one with the highest or lowest average, depending on the 'extreme' parameter.
     *
     * @param src      the path to the CSV file
     * @param splitter the delimiter used in the file (e.g., ";")
     * @param extreme  indicates whether to find the best("max") or the worst("min") average rated studio
     * @throws FileNotFoundException if the file cannot be read
     */
    private static void printExtremesInFile(String src, String splitter, String extreme) throws FileNotFoundException {
        String[][] file = fileToMatrix(src, splitter, 7);
        String[][] averageRating = new String[countLinesInFile(src)][2];

        double average;
        int y = 0;

        for (int i = 1; i < file.length; i++) {
            if (!isInMatrix(averageRating, file[i][5])) {
                averageRating[y][0] = file[i][5];
                average = calcAverage(file, file[i][5]);
                averageRating[y][1] = String.valueOf(average);
                y++;
            }
        }

        findAndPrintTheRightStudio(averageRating, extreme);
    }

    /**
     * Finds and prints the studio with either the highest or lowest average rating.
     *
     * @param averageRating a 2D array where each row contains [studio name, average rating as string]
     * @param extreme       indicates which value to find: "max" for highest, "min" for lowest
     */
    private static void findAndPrintTheRightStudio(String[][] averageRating, String extreme) {
        int line = 0;
        double target = Double.parseDouble(averageRating[0][1]);

        for (int i = 0; i < averageRating.length; i++) {
            if (averageRating[i][1] == null) continue;

            double rating = Double.parseDouble(averageRating[i][1]);

            if (extreme.equals("max") && rating > target) {
                target = rating;
                line = i;
            }

            if (extreme.equals("min") && rating < target) {
                target = rating;
                line = i;
            }
        }

        headerUser();
        if (extreme.equals("max"))
            System.out.println("\n\n     O estúdio com maior classificação é " + averageRating[line][0] + " com " +
                    averageRating[line][1] + ".");
        if (extreme.equals("min"))
            System.out.println("\n\n     O estúdio com menor classificação é " + averageRating[line][0] + " com " +
                    averageRating[line][1] + ".");
    }

    /**
     * Calculates the average rating for all rows where the target value matches in column 5 (studio).
     *
     * @param file   the data matrix
     * @param target the studio name to filter by
     * @return the average of ratings found in column 2 for the matching rows
     */
    private static double calcAverage(String[][] file, String target) {
        int[] index = findPositionOccurrencesInMatrix(file, target, 5); // Column 5 = Studio
        double sum = 0;

        for (int i = 0; i < index.length; i++) {
            sum += Double.parseDouble(file[index[i]][2]); // Column 2 = Rating
        }

        return sum / index.length; // Return average
    }

    /**
     * Displays the graphic menu for specific films.
     */
    private static void graphicMenu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int option;

        do {
            headerUser();
            headerMenu();
            System.out.println("\n\n                          Escolha o filme que procura:\n");
            System.out.println("                          #1- Harry Potter");
            System.out.println("                          #2- Interstellar");
            System.out.println("                          #3- Lord of The Rings");
            System.out.println("                          #4- Star Wars");
            System.out.println("\n                          #0- Voltar");
            System.out.print("\n                          Opcao: ");
            option = input.nextInt();
        } while (option < 0 || option > 4);
        if (option == 0)
            userMenu(); // Return to user menu
        graphicMenuDispacher(option); // Forward to correct graph
    }

    /**
     * Handles the user's selection in the graphic menu by displaying the corresponding movie catalog.
     *
     * @param option the selected menu option (1 to 4), each corresponding to a movie franchise
     * @throws FileNotFoundException if the selected text file cannot be found
     */
    private static void graphicMenuDispacher(int option) throws FileNotFoundException {
        String harryPotter = "IMDV/CatalogoGrafico/HarryPotter.txt";
        String interstellar = "IMDV/CatalogoGrafico/Interstellar.txt";
        String lordoftherings = "IMDV/CatalogoGrafico/LordOfTheRings.txt";
        String starwars = "IMDV/CatalogoGrafico/StarWars.txt";

        switch (option){
            case 1:
                cleanConsole();
                printContent(harryPotter, 0);
                stop();
                graphicMenu();
                break;
            case 2:
                cleanConsole();
                printContent(interstellar, 0);
                stop();
                graphicMenu();
                break;
            case 3:
                cleanConsole();
                printContent(lordoftherings, 0);
                stop();
                graphicMenu();
                break;
            case 4:
                cleanConsole();
                printContent(starwars, 0);
                stop();
                graphicMenu();
                break;
        }
    }
}
