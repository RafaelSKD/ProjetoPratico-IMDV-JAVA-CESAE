package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class files {
    /**
     * Reads a file and stores its content into a matrix.
     *
     * @param src      the path to the source file to be read
     * @param splitter the delimiter used to separate values in the file
     * @param matriz   the matrix where the parsed data will be stored
     * @param param    the number of expected parameters (columns) per line
     * @throws IOException if an I/O error occurs during file reading
     */
    public static void matrixToFile(String src, String splitter, String[][] matrix, int param) throws IOException {
        cleanFile(src);
        FileWriter fileWriter = new FileWriter(src);

        int x = 0, y;
        while (x < matrix.length){
            y = 0;
            while (y < param){
                if (matrix[x][y].isEmpty())
                    break;
                fileWriter.append(matrix[x][y]);
                if (y < param - 1)
                    fileWriter.append(splitter);
                y++;
                if (y == param)
                    fileWriter.append("\n");
            }
            x++;
        }
        fileWriter.close();
    }
    /**
     * Reads a text file and converts its contents into a 2D string array (matrix),
     * splitting each line using the specified delimiter.
     *
     * @param src      the path to the source file to be read
     * @param splitter the delimiter used to separate values in each line
     * @param param    the number of expected parameters (columns) per line
     * @return a 2D string array containing the file's content
     * @throws FileNotFoundException if the specified file cannot be found
     */
    public static String[][] fileToMatrix(String src, String splitter, int param) throws FileNotFoundException {
        Scanner data = new Scanner(new File(src));
        int i = 0;

        int lines = countLinesInFile(src);
        String[][] arrayFile = new String[lines][param];

        while (data.hasNextLine()) {
            String line = data.nextLine();
            String[] parts = line.split(splitter);
            arrayFile[i] = parts;
            i++;
        }
        data.close();
        return(arrayFile);
    }
    /**
     * Clears the contents of the specified file by overwriting it with an empty string.
     *
     * @param src the path to the file to be cleared
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void cleanFile(String src) throws IOException {
        FileWriter fileWriter = new FileWriter(src, false);

        fileWriter.write("");
        fileWriter.close();
    }
    /**
     * Counts the number of lines in a given text file.
     *
     * @param src the path to the source file
     * @return the total number of lines in the file
     * @throws FileNotFoundException if the specified file cannot be found
     */
    public static int countLinesInFile(String src) throws FileNotFoundException {
        int count = 0;
        File file = new File(src);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            scanner.nextLine();  // Move para a proxima linha
            count++;
        }

        scanner.close();
        return count;
    }
}
