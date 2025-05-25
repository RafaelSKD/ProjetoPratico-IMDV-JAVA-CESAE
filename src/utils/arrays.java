package utils;

/**
 * Utility class with methods for working with arrays and matrices of strings.
 */
public class arrays {

    /**
     * Checks if a given target string is present anywhere in the matrix.
     *
     * @param matrix   a 2D array of strings to be searched
     * @param target   the string to search for
     * @return true if target is found in any position of the matrix, false otherwise
     */
    public static boolean isInMatrix(String[][] matrix, String target) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] != null) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (target.equals(matrix[i][j])) { // critical: direct equality check
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if a target string exists in a 1D array.
     *
     * @param array  the array to search in
     * @param target the string to look for
     * @return true if the target exists in the array, false otherwise
     */
    public static boolean isInArray(String[] array, String target) {
        for (int i = 0; i < array.length; i++) {
            if (target.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds all row indices where the target value occurs in a specific column (position) of the matrix.
     *
     * @param matrix   the matrix to search in
     * @param target   the string to match
     * @param position the column index to check in each row
     * @return an array of row indices where the target is found in the given column
     */
    public static int[] findPositionOccurrencesInMatrix(String[][] matrix, String target, int position) {
        int count = countOccurrencesInMatrix(matrix, position, target); // Get how many times it occurs
        int[] listOfOccurrences = new int[count]; // Prepare array to hold result indices
        int x = 0;

        for (int i = 0; i < matrix.length; i++) {
            if (position < matrix[i].length && target.equals(matrix[i][position])) {
                listOfOccurrences[x++] = i; // Store row index where match was found
            }
        }
        return listOfOccurrences;
    }

    /**
     * Counts how many times the target string appears in the given column of the matrix.
     *
     * @param matrix   the matrix to search
     * @param position the column index to check
     * @param target   the string to look for
     * @return the number of times the target is found at the specified column
     */
    private static int countOccurrencesInMatrix(String[][] matrix, int position, String target) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (position < matrix[i].length && target.equals(matrix[i][position])) {
                count++;
            }
        }
        return count;
    }
}
