package tasks;

import tasks.basics.numbers.Factorial;
import tasks.basics.numbers.IsPrime;

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
}
