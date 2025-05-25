package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Utility class for file operations like reading file content into arrays and counting lines.
 */
public class files {

    /**
     * Reads a text file and converts its contents into a 2D string array (matrix),
     * splitting each line using the specified delimiter.
     *
     * @param src      the path to the source file to be read
     * @param splitter the delimiter used to separate values in each line (e.g., ";" for CSV)
     * @param param    the number of expected parameters (columns) per line
     * @return a 2D string array containing the file's content split by line and delimiter
     * @throws FileNotFoundException if the specified file cannot be found
     */
    public static String[][] fileToMatrix(String src, String splitter, int param) throws FileNotFoundException {
        Scanner data = new Scanner(new File(src)); // Read the file
        int i = 0;

        int lines = countLinesInFile(src); // Get total lines in file
        String[][] arrayFile = new String[lines][param]; // Initialize array to hold data

        while (data.hasNextLine()) {
            String line = data.nextLine(); // Read each line
            String[] parts = line.split(splitter); // Split line into parts by delimiter
            arrayFile[i] = parts; // Store in matrix
            i++;
        }
        data.close();
        return arrayFile;
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
        Scanner scanner = new Scanner(file); // Open scanner to read file line by line

        while (scanner.hasNextLine()) {
            scanner.nextLine();  // Read line (ignored), just to increment count
            count++;
        }
        scanner.close();
        return count;
    }
}
