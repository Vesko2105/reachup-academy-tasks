package tasks.basics.printing;

public class MatrixOperations {
    static void printByRows(char[][] matrix) {
        for (char[] array : matrix) {
            for (char character : array) {
                System.out.print(character + " ");
            }
            System.out.println();
        }
    }

    static void printByColumns(char[][] matrix) {
        for (int column = 0; column < matrix[0].length; column++) {
            for (char[] chars : matrix) {
                System.out.print(chars[column] + " ");
            }
            System.out.println();
        }
    }

    static void printByRowsRightLeft(char[][] matrix) {
        for (char[] chars : matrix) {
            for (int column = chars.length - 1; column >= 0; column--) {
                System.out.print(chars[column] + " ");
            }
            System.out.println();
        }
    }

    static void printByColumnsRightLeft(char[][] matrix) {
        for (int column = 0; column < matrix[0].length; column++) {
            for (int row = matrix.length - 1; row >= 0; row--) {
                System.out.print(matrix[row][column] + " ");
            }
            System.out.println();
        }
    }

    static void printByRowsBottomTop(char[][] matrix) {
        for (int row = matrix.length - 1; row >= 0; row--) {
            for(char character : matrix[row]) {
                System.out.print(character);
            }
            System.out.println();
        }
    }

    static void printByColumnsBottomTop(char[][] matrix) {
        for (int column = matrix[0].length - 1; column >= 0; column--) {
            for (char[] chars : matrix) {
                System.out.print(chars[column] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'},
                {'t', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 't'},
                {'e', 's', 't', ' ', 'f', 'o', 'r', ' ', 'p', 'r', 'i'},
                {'n', 't', 'i', 'n', 'g', ' ', 'a', ' ', 'm', 'a', 't'},
                {'r', 'i', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
        printByColumnsBottomTop(matrix);
    }
}
