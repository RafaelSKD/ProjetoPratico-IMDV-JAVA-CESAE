package menus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static main.main.secondMain;
import static menus.loginUser.userLogin;
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
            System.out.println("\n\n                          Escolha a forma da procura:\n");
            System.out.println("                    #1- Registar Novo Utilizador ⭐");
            System.out.println("                    #2- Imprimir Catálogo 📚");
            System.out.println("                    #3- Imprimir Catálogos Gráficos 📊");
            System.out.println("                    #4- Imprimir Melhor Estúdio 🏆");
            System.out.println("                    #5- Imprimir Pior Estúdio 💔");
            System.out.println("                    #6- Imprimir Crítica Mais Recente 📝");
            System.out.println("                    #7- Imprimir Crítica Do Filme mais Recente 🆕");
            System.out.println("                    #8- Quiz 🎯");
            System.out.println("                    #9- Imprimir Catálogo Estúdio 🏢");
            System.out.println("                    #10- Imprimir Catálogo Categoria 🎭");
            System.out.println("\n                    #0- Logout 🚪");
            System.out.print("\n                    Opcao: ");
            option = input.nextInt();
        } while (option < 0 || option > 10); // Input validation
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
                userLogin();
                break;
            case 2:
                printContentListOf(src, splitter, 1, 2);  // print all movies with ratings
                stop();
                break;
            case 3:
                graphicMenu(); // Navigates to graphic submenu
                break;
            case 4:
                printExtremesInFile(src, splitter, "max");  // print best studio by ratings
                stop();
                break;
            case 5:
                printExtremesInFile(src, splitter, "min");  // print worst studio by ratings
                stop();
                break;
            case 6:
                printMostRecentRating(src); // prints the last entry
                stop();
                break;
            case 7:
                printMostRecentMovieRating(src); // prints the rating of most recent movie
                stop();
                break;
            case 8:
                quiz(); // Launch quiz feature
                break;
            case 9:
                printFilter(src, "studio"); // Prints all movies filter by studio
                break;
            case 10:
                printFilter(src, "category"); // Prints all movies filter by category
                break;
        }

        userMenu(); // Re-display the menu after operation
    }

    /**
     * Prints the most recent movie (based on the release year) and its rating from the data file.
     *
     * @param src the path to the movie CSV file
     * @throws FileNotFoundException if the file cannot be found
     */
    private static void printMostRecentMovieRating(String src) throws FileNotFoundException {
        String[][] data = fileToMatrix(src, ";", 8); // Read file into matrix (8 columns)

        int mostRecentYear = Integer.MIN_VALUE;
        int mostRecentIndex = -1;

        for (int i = 1; i < data.length; i++) { // Skip header = 1
            int year = Integer.parseInt(data[i][4]); // Column 4 = year
            if (year > mostRecentYear) {
                mostRecentYear = year;
                mostRecentIndex = i;
            }
        }

        if (mostRecentIndex != -1) {
            String title = data[mostRecentIndex][1];   // Column 1 = title
            String rating = data[mostRecentIndex][2];  // Column 2 = rating
            String year = data[mostRecentIndex][4];    // Column 4 = year

            headerUser();
            System.out.println("\n\n                         🎬 Filme mais recente avaliado:\n");
            System.out.println("                           - 🎬 Título : " + title);
            System.out.println("                           - 📆 Ano   : " + year);
            System.out.println("                           - ⭐ Rating : " + rating);
        }
    }


    /**
     * Filters and prints a list of movies based on the selected filter:
     * either by studio (grouped by genre) or by genre (listing all matching titles).
     *
     * @param src    the path to the CSV file containing movie data
     * @param filter the filter type selected by the user: "studio" or "category"
     * @throws FileNotFoundException if the CSV file cannot be located
     */
    public static void printFilter(String src, String filter) throws FileNotFoundException {
        Scanner input = new Scanner(System.in); // Read user input
        String[][] data = fileToMatrix(src, ";", 8); // Read file into matrix (8 columns)
        headerUser(); // Display user UI header
        String option;
        int[] index;

        if (filter.equals("studio")) {
            System.out.println("\n\n                    Qual o Estudio que deseja ver o catalogo?");
            System.out.print("\n                             🏢 Estudio: ");
            option = input.nextLine(); // Read studio name from user
            index = findPositionOccurrencesInMatrix(data, option, 5); // Column 5 = studio

            // Print all movies from this studio grouped by genre
            printContentByStudioGroupedByCategory(data, index);
            stop(); // Pause before returning
        }

        if (filter.equals("category")) {
            System.out.println("\n\n                    Qual o genero que deseja ver o catalogo?");
            System.out.print("\n                             🎭 Genero: ");
            option = input.nextLine(); // Read genre name from user
            index = findPositionOccurrencesInMatrix(data, option, 7); // Column 7 = genre

            // Print all movies of this genre
            printContentByCategoryOnly(data, index);
            stop(); // Pause before returning
        }
    }

    /**
     * Prints movie titles grouped by category (genre) for a given studio.
     * Only categories present within the selected studio are printed.
     *
     * @param data  the full data matrix, including headers in row 0
     * @param index array of row indices where the studio matches the selected one
     */
    private static void printContentByStudioGroupedByCategory(String[][] data, int[] index) {
        String[] categories = new String[index.length]; // Tracks printed categories to avoid duplicates
        int y = 0, flag;

        System.out.println("\n");

        for (int i = 0; i < index.length; i++) {
            String category = data[index[i]][7]; // Column 7 = genre

            flag = 0;
            // Check if this category has already been printed
            for (int j = 0; j < y; j++) {
                if (categories[j].equals(category)) {
                    flag = 1; // Found a duplicate
                    break;
                }
            }

            if (flag == 0) {
                categories[y++] = category; // Register new unique category
                System.out.println("\n                                   🍿 " + category + ":\n");

                // Print all titles in the same category
                for (int k = 0; k < index.length; k++) {
                    if (data[index[k]][7].equals(category)) { // Match category
                        System.out.println("           - " + data[index[k]][1]); // Column 1 = movie title
                    }
                }
            }
        }
    }

    /**
     * Prints a list of movie titles that belong to a specific category (genre).
     * Assumes all given indices belong to the same category, which is shown as a section header.
     *
     * @param data  the full data matrix, including headers in row 0
     * @param index array of row indices where the category matches the selected one
     */
    private static void printContentByCategoryOnly(String[][] data, int[] index) {
        System.out.println("\n");

        if (index.length > 0) {
            String category = data[index[0]][7]; // Column 7 = category (genre)
            System.out.println("\n                                   🍿 " + category + ":\n");

            for (int i = 0; i < index.length; i++) {
                System.out.println("           - " + data[index[i]][1]); // Column 1 = movie title
            }
        }
    }


    /**
     * Prints the most recent movie rating from the last row of the data file.
     *
     * @param src the path to the CSV file
     * @throws FileNotFoundException if the file cannot be found
     */
    private static void printMostRecentRating(String src) throws FileNotFoundException {
        String[][] data = fileToMatrix(src, ";", 8); // Read data into matrix
        int lastIndex = data.length - 1; // Last row (excluding header)

        if (lastIndex > 0) {
            String title = data[lastIndex][1];   // Column 1 = Title
            String rating = data[lastIndex][2];  // Column 2 = Rating
            String year = data[lastIndex][4];    // Column 4 = Year
            String studio = data[lastIndex][5];  // Column 5 = Studio
            String genre = data[lastIndex][7];   // Column 7 = Genre

            headerUser();
            System.out.println("\n                         📝 Última crítica adicionada:\n");
            System.out.println("                         - 🎬 Título  : " + title);
            System.out.println("                         - ⭐ Rating  : " + rating);
            System.out.println("                         - 📆 Ano    : " + year);
            System.out.println("                         - 🏢 Estúdio: " + studio);
            System.out.println("                         - 🎭 Género : " + genre);
        }
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
            System.out.println("\n\n           🏆 O estúdio com maior classificação é " + averageRating[line][0] + " com " +
                    averageRating[line][1] + ".");
        if (extreme.equals("min"))
            System.out.println("\n\n          💔 O estúdio com menor classificação é " + averageRating[line][0] + " com " +
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
            System.out.println("                          #1- 🧙‍♂️ Harry Potter");
            System.out.println("                          #2- 🚀 Interstellar");
            System.out.println("                          #3- 💍 Lord of The Rings");
            System.out.println("                          #4- 🌌 Star Wars");
            System.out.println("\n                          #0- 🔙 Voltar");
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
