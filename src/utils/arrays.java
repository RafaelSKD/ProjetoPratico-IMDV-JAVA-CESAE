package utils;

public class arrays {
    /**
     * Checks whether a given target string exists within the specified array.
     *
     * Null entries in the array are safely ignored during comparison.
     *
     * @param array  the array to search through
     * @param target the string value to find
     * @return true if the target is found in the array; false otherwise
     */
    public static boolean isInMatrix(String[][] matrix, String target, int position) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] != null && matrix[i].equals(target))
                return true;
        }
        return false;
    }
}
