package pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import static menus.menuUser.userMenu;
import static utils.console.stop;
import static utils.files.countLinesInFile;
import static utils.files.fileToMatrix;
import static utils.print.*;

/**
 * This class handles the Movie Quiz interface, logic, scoring, and retry flow.
 */
public class quiz {

    public static void main(String[] args) throws FileNotFoundException {
        quiz(); // Starts the quiz (test purpose)
    }

    /**
     * Main quiz logic: displays each question, processes answers, updates score.
     *
     * @throws FileNotFoundException if the Quiz.csv file is not found
     */
    public static void quiz() throws FileNotFoundException {
        Scanner input = new Scanner(System.in); // For reading user input
        String[][] quizData = fileToMatrix("IMDV/Quiz.csv", ";", 6);

        int option, correctAnswers = 0;
        int questions = countLinesInFile("IMDV/Quiz.csv") - 1;

        startGame(); // Display quiz start screen

        for (int i = 1; i < quizData.length; i++) { // Skip header line
            do {
                headerQuiz(); // UI Header
                System.out.print("\n                                                                           " + correctAnswers + "/" + questions);
                System.out.println("\n     Pergunta " + (correctAnswers + 1) + ":\n     " + quizData[i][0] + "\n");

                // Display answer choices
                System.out.println("\n\n      Escolha:\n");
                System.out.println("          #1- " + quizData[i][1]);
                System.out.println("          #2- " + quizData[i][2]);
                System.out.println("          #3- " + quizData[i][3]);
                System.out.println("          #4- " + quizData[i][4]);

                System.out.print("\n\n\n      Opcao: ");
                option = input.nextInt(); // User selects an answer
            } while (option < 1 || option > 4); // Validate option range

            // Check if answer is correct
            if (Integer.parseInt(quizData[i][5]) == option) {
                headerQuiz();
                System.out.print("\n\n                         🎬 Resposta correta! Parabéns.");
                correctAnswers++;
                stop(); // Pause to show feedback
            } else {
                headerQuiz();
                String answer = quizData[i][Integer.parseInt(quizData[i][5])];
                System.out.print("\n\n                              ❌ Resposta errada!\n\n\n" +
                        "                       A resposta certa era: " + answer + ".");
                stop(); // Pause to show feedback
            }
        }

        endGame(correctAnswers, questions); // Show results and grade
        tryAgain(); // Ask user to repeat or go back
    }

    /**
     * Displays the intro screen for the quiz game.
     */
    private static void startGame() {
        headerQuiz();
        System.out.println("\n\n\n                    ███████╗████████╗ █████╗ ██████╗ ████████╗");
        System.out.println("                    ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗╚══██╔══╝");
        System.out.println("                    ███████╗   ██║   ███████║██████╔╝   ██║   ");
        System.out.println("                    ╚════██║   ██║   ██╔══██║██╔══██╗   ██║   ");
        System.out.println("                    ███████║   ██║   ██║  ██║██║  ██║   ██║   ");
        System.out.println("                    ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ");
        System.out.println("                                                              ");
        System.out.println("                         ██████╗ ██╗   ██╗██╗███████╗         ");
        System.out.println("                        ██╔═══██╗██║   ██║██║╚══███╔╝         ");
        System.out.println("                        ██║   ██║██║   ██║██║  ███╔╝          ");
        System.out.println("                        ██║▄▄ ██║██║   ██║██║ ███╔╝           ");
        System.out.println("                        ╚██████╔╝╚██████╔╝██║███████╗         ");
        System.out.println("                         ╚══▀▀═╝  ╚═════╝ ╚═╝╚══════╝         ");
        stop(); // Pause before starting quiz
    }

    /**
     * Asks the user if they want to repeat the quiz or return to the main menu.
     *
     * @throws FileNotFoundException in case the quiz restarts and the file is missing
     */
    private static void tryAgain() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        char option;

        do {
            headerQuiz();
            System.out.print("\n\n                   🔁 Tenta fazer o quiz novamente para melhorar!");
            System.out.print("\n\n                                   ✅ S / N ❌\n\n\n");
            option = input.nextLine().charAt(0); // Get first character of input
        } while (option != 's' && option != 'S' && option != 'n' && option != 'N'); // Valid responses
        stop();
        if (option == 's' || option == 'S')
            quiz();      // Restart quiz
        else
            userMenu();  // Go back to user menu
    }

    /**
     * Shows the final score and feedback based on performance.
     *
     * @param correctAnswers the number of correct responses from the user
     * @param questions the total number of quiz questions
     */
    private static void endGame(int correctAnswers, int questions) {
        // These values are used to classify user performance into different feedback levels.
        double correct90 = questions * 0.9;
        double correct75 = questions * 0.75;
        double correct50 = questions * 0.5;
        double correct25 = questions * 0.25;

        int grade;
        if (correctAnswers == questions) {
            grade = 100;
        } else if (correctAnswers >= correct90) {
            grade = 90;
        } else if (correctAnswers >= correct75) {
            grade = 75;
        } else if (correctAnswers >= correct50) {
            grade = 50;
        } else if (correctAnswers >= correct25) {
            grade = 25;
        } else if (correctAnswers > 0) {
            grade = 10;
        } else {
            grade = 0;
        }

        headerQuiz(); // UI Header
        System.out.print("\n                                                                           " +
                correctAnswers + "/" + questions);

        // Grade feedback message
        switch (grade) {
            case 100:
                System.out.println("\n\n\n                      🎉 Excelente! Acertaste todas as perguntas!\n\n");
                break;
            case 90:
                System.out.println("\n\n\n                            👏 Muito bom! Quase perfeito.\n\n");
                break;
            case 75:
                System.out.println("\n\n\n                       👍 Bom resultado! Tens bons conhecimentos.\n\n");
                break;
            case 50:
                System.out.println("\n\n\n                            🙂 Nada mal, mas podes melhorar.\n\n");
                break;
            case 25:
                System.out.println("\n\n\n                            😕 Precisas rever alguns temas.\n\n");
                break;
            case 10:
                System.out.println("\n\n\n                  📉 Acertaste alguma... mas ainda há caminho.\n\n");
                break;
            case 0:
                System.out.println("\n\n\n                   💤 Não acertaste nenhuma... tenta rever os filmes!\n\n");
                break;
        }

        stop(); // Pause before retry option
    }
}
