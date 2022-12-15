package tasks;

import tasks.basics.numbers.Factorial;
import tasks.basics.numbers.IsPrime;

import java.util.Arrays;

public class Utils {

    private Utils() {

    }

    public static boolean isPrime(int number) {
        return IsPrime.isPrime(number);
    }

    public static double myRound(double value, int places) {
        System.out.println("value = " + value + ", places = " + places);
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public static long fact(int number) {
        return Factorial.fact(number);
    }

    public static char[][] deepCopyOf(char[][] matrix) {
        char[][] matrixClone = new char[matrix.length][matrix[0].length];
        for (int i = 0; i < matrixClone.length; i++) {
            matrixClone[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        return matrixClone;
    }
}
