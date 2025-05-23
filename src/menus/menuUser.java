package menus;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static main.main.secondMain;
import static utils.arrays.isInArray;
import static utils.arrays.isInMatrix;
import static utils.console.cleanConsole;
import static utils.console.stop;
import static utils.files.countLinesInFile;
import static utils.files.fileToMatrix;
import static utils.print.printContent;
import static utils.print.printContentListOf;

public class menuUser {
    /**
     * Main method used to launch the client (user) menu interface for testing.
     *
     * @param args command-line arguments (not used)
     * @throws FileNotFoundException if any required file for the user menu operations is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        userMenu();
    }

    /**
     * Displays the client (user) menu interface and handles option selection.
     *
     * The user is presented with several content browsing options such as viewing catalogs,
     * checking studio ratings, recent reviews, and participating in a quiz.
     * The input is validated to ensure a valid option is selected.
     * If the user chooses to log out (option 0), the method redirects to the welcome page.
     *
     * @throws FileNotFoundException if any required file for menu operations is not found
     */
    public static void userMenu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
                                                                                                // Menu for client guest
        int option;
        do {
            cleanConsole();
            System.out.println("================================ IMDV ================================");
            System.out.println("____________________           CLIENTE            ____________________");
            System.out.println("\n____________________             MENU             ____________________");
            System.out.println("                       Escolha a forma da procura:\n");
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
        } while (option < 0 || option > 8);

        if (option == 0)                                                                      // redirects to login menu
            secondMain();

        userMenuDispacher(option);
    }

    public static void userMenuDispacher(int option) throws FileNotFoundException {
        String src = "IMDV/IMDV.csv";
        String splitter = ";";

        switch (option){
            case 1:  // Case 1: Print the full catalog of movies
                cleanConsole();
                printContentListOf(src, splitter, 1, 2);
                stop();
                userMenu();
                break;
            case 2:  // Case 2: Print the catalog in graphical format
                graphicMenu();
                break;
            case 3:  // Case 3: Print the studio with the best average rating
                printExtremesInFile(src, splitter, "max");
                break;
            case 4:  // Case 4: Print the studio with the worst average rating
                printExtremesInFile(src, splitter, "min");
                break;
            case 5:  // Case 5: Print the most recent review submitted
                break;
            case 6:  // Case 6: Launch a quiz related to movies
                break;
            case 7:  // Case 7: Print the catalog filtered by studio
                break;
            case 8:  // Case 8: Print the catalog filtered by category
                break;
        }
    }

    private static void printExtremesInFile(String src, String splitter, String extreme) throws FileNotFoundException {
        String[][] file = fileToMatrix(src, splitter, 7);
        String[][] averageRating = new String[countLinesInFile(src)][2];

        int y = 0, rating, oldRating;
        for (int i = 1; i < file.length; i++){
            if(isInMatrix(averageRating, file[i][5], 5)){
                rating = Integer.parseInt(averageRating[findTargetPositionInMatrix(averageRating, file[i][1])]) +
                Integer.parseInt(file);
            }
        }

    }

    private static int findTargetPositionInMatrix(String[][] matrix, String target){
        for (int i = 0; i < matrix.length; i++){
            if (target.equals(matrix[i]))
                return i;
        }
        return -1;
    }

    private static void graphicMenu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int option;
        do {
            cleanConsole();
            System.out.println("================================ IMDV ================================");
            System.out.println("____________________           CLIENTE            ____________________");
            System.out.println("\n____________________             MENU             ____________________");
            System.out.println("                      Escolha o filme que procura:\n");
            System.out.println("                    #1- Harry Potter");
            System.out.println("                    #2- Interstellar");
            System.out.println("                    #3- Lord of The Rings");
            System.out.println("                    #4- Star Wars");
            System.out.println("\n                    #0- Voltar");
            System.out.print("\n                    Opcao: ");
            option = input.nextInt();
        } while (option < 0 || option > 4);

        if (option == 0)                                                                      // redirects to login menu
            userMenu();

        graphicMenuDispacher(option);
    }

    private static void graphicMenuDispacher(int option) throws FileNotFoundException {
        String harryPotter = "IMDV/CatalogoGrafico/HarryPotter.txt";
        String interstellar = "IMDV/CatalogoGrafico/Interstellar.txt";
        String lordoftherings = "IMDV/CatalogoGrafico/LordOfTheRings.txt";
        String starwars = "IMDV/CatalogoGrafico/StarWars.txt";

        switch (option){
            case 1:  // Case 1: Show Harry Potter Graphic
                cleanConsole();
                printContent(harryPotter);
                stop();
                graphicMenu();
                break;
            case 2:  // Case 2: Show Interstellar Graphic
                cleanConsole();
                printContent(interstellar);
                stop();
                graphicMenu();
                break;
            case 3:  // Case 3: Show Lord of The Rings Graphic
                cleanConsole();
                printContent(lordoftherings);
                stop();
                graphicMenu();
                break;
            case 4:  // Case 4: Show Star Wars Graphic
                cleanConsole();
                printContent(starwars);
                stop();
                graphicMenu();
                break;
        }
    }
}
